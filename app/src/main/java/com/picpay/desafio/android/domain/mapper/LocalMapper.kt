package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.data.local.model.UserEntity
import com.picpay.desafio.android.domain.model.User

fun User.toEntity() : UserEntity =
    UserEntity(
        userId = this.id,
        img = this.img,
        name = this.name,
        username = this.username
    )

fun UserEntity.toDomain() : User =
    User(
        id = this.userId,
        img = this.img,
        name = this.name,
        username = this.username
    )

fun List<UserEntity>.toDomain() : List<User> =
    this.map { it.toDomain() }