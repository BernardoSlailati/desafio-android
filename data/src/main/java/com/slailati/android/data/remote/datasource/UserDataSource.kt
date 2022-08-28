package com.slailati.android.data.remote.datasource

import com.slailati.android.data.remote.model.UserApi

interface UserDataSource {

    suspend fun getContacts(): List<UserApi>

}