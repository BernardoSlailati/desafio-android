package com.slailati.android.domain.utils.datasource

import com.slailati.android.data.remote.datasource.UserDataSource
import com.slailati.android.data.remote.model.UserApi
import com.slailati.android.domain.utils.fakeUserApiListAllCorrect

class FakeUserDataSource: UserDataSource {

    override suspend fun getContacts(): List<UserApi> = fakeUserApiListAllCorrect

}