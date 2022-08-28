package com.slailati.android.domain.model

data class User(
    val img: String,
    val name: String,
    val id: Int,
    val username: String,
    var isFavorite: Boolean = false
)