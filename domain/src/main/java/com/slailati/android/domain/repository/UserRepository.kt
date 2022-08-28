package com.slailati.android.domain.repository

import com.slailati.android.domain.model.User

interface UserRepository {

    suspend fun getContacts(): List<User>

    suspend fun getFavorites(): List<User>

    suspend fun saveFavorite(user: User)

    suspend fun removeFavorite(userId: Int)

}