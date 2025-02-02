package com.picpay.desafio.android.utils.datasource

import com.slailati.android.data.local.datasource.FavoriteDataSource
import com.slailati.android.data.local.model.UserEntity

class FakeFavoriteDataSource(var favoriteUsers: MutableList<UserEntity> = mutableListOf()): FavoriteDataSource {

    override suspend fun insert(user: UserEntity): Long {
        favoriteUsers.add(user)
        return favoriteUsers.indexOf(user).toLong()
    }

    override suspend fun delete(user: UserEntity) {
        favoriteUsers.remove(user)
    }

    override suspend fun deleteByUserId(userId: Int) {
        favoriteUsers.removeIf { it.userId == userId }
    }

    override suspend fun getAll(): List<UserEntity> = favoriteUsers

}