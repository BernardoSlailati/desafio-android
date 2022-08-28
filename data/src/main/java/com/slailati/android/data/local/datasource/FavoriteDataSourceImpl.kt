package com.slailati.android.data.local.datasource

import com.slailati.android.data.local.database.MainDatabase
import com.slailati.android.data.local.model.UserEntity

class FavoriteDataSourceImpl(
    private val mainDatabase: MainDatabase
): FavoriteDataSource {
    override suspend fun insert(user: UserEntity): Long =
        mainDatabase.favoriteContactsDao.insert(user)

    override suspend fun delete(user: UserEntity)  =
        mainDatabase.favoriteContactsDao.delete(user)

    override suspend fun deleteByUserId(userId: Int) =
        mainDatabase.favoriteContactsDao.deleteById(userId)

    override suspend fun getAll(): List<UserEntity> =
        mainDatabase.favoriteContactsDao.getAll()

}