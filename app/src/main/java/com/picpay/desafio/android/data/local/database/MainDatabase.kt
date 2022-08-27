package com.picpay.desafio.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.database.dao.FavoriteContactsDao
import com.picpay.desafio.android.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract val favoriteContactsDao: FavoriteContactsDao
}
