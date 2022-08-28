package com.slailati.android.domain.repository

import com.slailati.android.data.local.datasource.FavoriteDataSource
import com.slailati.android.data.remote.datasource.UserDataSource
import com.slailati.android.domain.mapper.toDomain
import com.slailati.android.domain.mapper.toDomainList
import com.slailati.android.domain.mapper.toEntity
import com.slailati.android.domain.model.User

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val favoriteDataSource: FavoriteDataSource
) : UserRepository {

    override suspend fun getContacts(): List<User> = try {
        userDataSource.getContacts().toDomainList()
    } catch (e: Exception) {
        emptyList()
    }

    override suspend fun getFavorites(): List<User> = favoriteDataSource.getAll().toDomain()

    override suspend fun saveFavorite(user: User) {
        favoriteDataSource.insert(user.toEntity())
    }

    override suspend fun removeFavorite(userId: Int) {
        favoriteDataSource.deleteByUserId(userId)
    }

}