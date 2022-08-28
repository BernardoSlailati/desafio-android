package com.slailati.android.domain.mapper

import org.junit.Assert
import org.junit.Test

internal class RemoteMapperTest {

    @Test
    fun `convert correct user api to domain correctly`() {
        val convertedFirstUserApiCorrectToDomain = fakeFirstUserApiCorrect.toDomain()

        Assert.assertEquals(convertedFirstUserApiCorrectToDomain, fakeFirstUserCorrect)
    }

    @Test
    fun `convert wrong user api to domain correctly`() {
        val convertedFirstUserApiWrongToDomain = fakeFirstUserApiWrong.toDomain()

        Assert.assertEquals(convertedFirstUserApiWrongToDomain, fakeFirstUserWrong)
    }

    @Test
    fun `convert all correct user api list to domain list correctly`() {
        val convertedUserApiAllCorrectListToDomainList = fakeUserApiListAllCorrect.toDomainList()

        Assert.assertEquals(
            convertedUserApiAllCorrectListToDomainList,
            fakeUserDomainListAllCorrect
        )
    }

    @Test
    fun `convert half correct user api list to domain list correctly`() {
        val convertedUserApiHalfCorrectListToDomainList = fakeUserApiListHalfCorrect.toDomainList()

        Assert.assertEquals(
            convertedUserApiHalfCorrectListToDomainList,
            fakeUserDomainListHalfCorrect
        )
    }

    @Test
    fun `convert all wrong user api list to domain list correctly`() {
        val convertedUserApiAllWrongListToDomainList = fakeUserApiListAllWrong.toDomainList()

        Assert.assertEquals(convertedUserApiAllWrongListToDomainList, fakeUserDomainListAllWrong)
    }

}