package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.data.remote.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<UserApi> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}