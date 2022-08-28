package com.slailati.android.data.local.datasource

import com.slailati.android.data.local.model.UserEntity

interface FavoriteDataSource {

    suspend fun insert(user: UserEntity): Long

    suspend fun delete(user: UserEntity)

    suspend fun deleteByUserId(userId: Int)

    suspend fun getAll(): List<UserEntity>

}