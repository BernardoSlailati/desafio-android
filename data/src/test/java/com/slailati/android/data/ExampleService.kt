package com.slailati.android.data

import com.slailati.android.data.remote.service.PicPayService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ExampleService(
    private val service: PicPayService
) {

//    fun example(): List<UserApi> {
//         TODO: como testar suspend function?!?!?!
//        val users = service.getContacts()
//
//        return users.body() ?: emptyList()
//    }

    @Test
    fun `call get contacts from service`() = runBlocking {
        val users = service.getContacts()

        Assert.assertEquals(null, users)
    }

}