package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.database.MainDatabase
import com.picpay.desafio.android.domain.mapper.toDomain
import com.picpay.desafio.android.domain.mapper.toEntity
import com.picpay.desafio.android.domain.model.User

class FavoriteDataSourceImpl(
    private val mainDatabase: MainDatabase
): FavoriteDataSource {
    override suspend fun insert(user: User): Long =
        mainDatabase.favoriteContactsDao.insert(user.toEntity())

    override suspend fun delete(user: User)  =
        mainDatabase.favoriteContactsDao.delete(user.toEntity())

    override suspend fun deleteByUserId(userId: Int) =
        mainDatabase.favoriteContactsDao.deleteById(userId)

    override suspend fun getAll(): List<User> =
        mainDatabase.favoriteContactsDao.getAll().toDomain()

}