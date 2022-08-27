package com.picpay.desafio.android.domain.repository

import android.util.AndroidRuntimeException
import com.picpay.desafio.android.data.remote.datasource.UserDataSource
import com.picpay.desafio.android.domain.mapper.toDomainList
import com.picpay.desafio.android.domain.model.User
import java.net.UnknownHostException

class UserRepositoryImpl(
    private val remoteDataSource: UserDataSource
): UserRepository {

    override suspend fun getContacts(): List<User> =  try { remoteDataSource.getContacts() } catch(e: UnknownHostException) { emptyList() }

}