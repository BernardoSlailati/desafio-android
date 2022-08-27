package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.data.remote.model.UserApi
import com.picpay.desafio.android.domain.model.User

fun UserApi.toDomain() : User =
    User(
        id = this.id ?: -1,
        img = this.img ?: "",
        name = this.name ?: "",
        username = this.username ?: ""
    )

fun List<UserApi>?.toDomainList() : List<User> = this?.map { it.toDomain() } ?: emptyList()