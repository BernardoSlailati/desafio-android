package com.picpay.desafio.android.utils.datasource

import com.slailati.android.data.remote.datasource.UserDataSource
import com.slailati.android.data.remote.model.UserApi

class FakeUserDataSource(var users: MutableList<UserApi> = mutableListOf()): UserDataSource {

    override suspend fun getContacts(): List<UserApi> = users.toList()

}