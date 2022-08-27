package com.picpay.desafio.android.data.remote.service

import com.picpay.desafio.android.data.remote.model.UserApi
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserApi>>

    @GET("users")
    suspend fun getContacts(): List<UserApi>?

}