package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.local.datasource.FavoriteDataSource
import com.picpay.desafio.android.data.remote.datasource.UserDataSource
import com.picpay.desafio.android.domain.model.User

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val favoriteDataSource: FavoriteDataSource
): UserRepository {

    override suspend fun getContacts(): List<User> =  try { userDataSource.getContacts() } catch(e: Exception) { emptyList() }

    override suspend fun getFavorites(): List<User> = favoriteDataSource.getAll()

    override suspend fun saveFavorite(user: User) { favoriteDataSource.insert(user) }

    override suspend fun removeFavorite(userId: Int)  { favoriteDataSource.deleteByUserId(userId) }

}