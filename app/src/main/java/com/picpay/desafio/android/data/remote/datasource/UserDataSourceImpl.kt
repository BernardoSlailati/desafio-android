package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.domain.mapper.toDomainList
import com.picpay.desafio.android.domain.model.User

class UserDataSourceImpl(
    private val picPayService: PicPayService
): UserDataSource {

    override suspend fun getContacts(): List<User> = picPayService.getContacts().toDomainList()

}