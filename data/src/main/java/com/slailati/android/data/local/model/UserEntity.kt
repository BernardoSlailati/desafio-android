package com.slailati.android.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_contacts")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "user_id") val userId: Int,
    val img: String,
    val name: String,
    @ColumnInfo(name = "user_name") val username: String
)