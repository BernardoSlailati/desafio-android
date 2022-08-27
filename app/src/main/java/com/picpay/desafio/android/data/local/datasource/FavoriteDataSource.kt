package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.domain.model.User

interface FavoriteDataSource {

    suspend fun insert(user: User): Long

    suspend fun delete(user: User)

    suspend fun deleteByUserId(userId: Int)

    suspend fun getAll(): List<User>

}