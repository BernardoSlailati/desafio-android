package com.slailati.android.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.slailati.android.data.remote.model.UserApi
import com.slailati.android.data.remote.service.PicPayService
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<List<UserApi?>?>>()
        val expectedUsers = emptyList<UserApi>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        // TODO: como testar funções suspensas????
//        whenever(api.getContacts()).thenReturn(call)

        // when
//        val users = service.example()

        // then
//        assertEquals(users, expectedUsers)
    }
}