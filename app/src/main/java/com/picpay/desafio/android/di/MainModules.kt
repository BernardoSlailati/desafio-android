package com.picpay.desafio.android.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.picpay.desafio.android.ui.extension.isInternetAvailable
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import com.slailati.android.data.local.database.MainDatabase
import com.slailati.android.data.local.database.dao.FavoriteContactsDao
import com.slailati.android.data.local.datasource.FavoriteDataSource
import com.slailati.android.data.local.datasource.FavoriteDataSourceImpl
import com.slailati.android.data.remote.datasource.UserDataSource
import com.slailati.android.data.remote.datasource.UserDataSourceImpl
import com.slailati.android.data.remote.service.PicPayService
import com.slailati.android.domain.repository.UserRepository
import com.slailati.android.domain.repository.UserRepositoryImpl
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataSourceModule = module {
    single<UserDataSource> { UserDataSourceImpl(get()) }
    single<FavoriteDataSource> { FavoriteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideFavoriteContactsDao(get()) }
}

fun provideDatabase(application: Application): MainDatabase {
    return Room.databaseBuilder(application, MainDatabase::class.java, "pic_pay_contacts_database")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideFavoriteContactsDao(database: MainDatabase): FavoriteContactsDao {
    return database.favoriteContactsDao
}

val networkModule = module {
    factory { provideOkHttpClient(androidContext()) }
    factory { providePicPayService(get()) }
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val onlineInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 // read from cache for 60 seconds even if there is internet connection
        response.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .removeHeader("Pragma")
            .build()
    }

    val offlineInterceptor = Interceptor { chain ->
        var request: Request = chain.request()
        if (!context.isInternetAvailable()) {
            val maxStale = 60 * 60 * 24 * 30 // offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
    val cache = Cache(context.cacheDir, cacheSize)

    return OkHttpClient().newBuilder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(offlineInterceptor)
        .addNetworkInterceptor(onlineInterceptor)
        .cache(cache)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun providePicPayService(okHttpClient: OkHttpClient): PicPayService =
    Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PicPayService::class.java)
