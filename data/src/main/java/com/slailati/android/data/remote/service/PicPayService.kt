package com.slailati.android.data.remote.service

import com.slailati.android.data.remote.model.UserApi
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getContacts(): List<UserApi?>?

}