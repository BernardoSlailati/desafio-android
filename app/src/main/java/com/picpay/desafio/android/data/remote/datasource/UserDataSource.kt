package com.picpay.desafio.android.data.remote.datasource

import com.picpay.desafio.android.domain.model.User

interface UserDataSource {

    suspend fun getContacts(): List<User>

}