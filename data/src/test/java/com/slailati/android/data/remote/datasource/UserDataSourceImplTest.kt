package com.slailati.android.data.remote.datasource

import com.slailati.android.data.remote.model.UserApi
import com.slailati.android.data.validation.validate
import org.junit.Assert
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `validate null user api list`() {
        val result: List<UserApi?>? = null

        Assert.assertEquals(result.validate(), emptyList<UserApi>())
    }

    @Test
    fun `validate user api list with nulls`() {
        val result: List<UserApi?> = listOf(null, fakeFirstUser, fakeSecondUser, null)

        Assert.assertEquals(result.validate().size, 2)
    }

    @Test
    fun `validate user list`() {
        val result: List<UserApi?> = listOf(fakeFirstUser, fakeSecondUser, fakeThirdUser)

        Assert.assertEquals(result.validate().size, 3)
    }

}