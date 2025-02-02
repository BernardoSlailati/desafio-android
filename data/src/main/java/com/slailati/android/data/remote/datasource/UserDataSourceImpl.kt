package com.slailati.android.data.remote.datasource

import com.slailati.android.data.remote.model.UserApi
import com.slailati.android.data.remote.service.PicPayService
import com.slailati.android.data.validation.validate

class UserDataSourceImpl(
    private val picPayService: PicPayService
) : UserDataSource {

    override suspend fun getContacts(): List<UserApi> =
        picPayService.getContacts().validate()

}