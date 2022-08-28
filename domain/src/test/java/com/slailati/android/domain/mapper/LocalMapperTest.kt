package com.slailati.android.domain.mapper

import org.junit.Assert
import org.junit.Test

internal class LocalMapperTest {

    @Test
    fun `convert user domain to entity correctly`() {
        val convertedFirstUserToEntity = fakeFirstUser.toEntity()

        Assert.assertEquals(convertedFirstUserToEntity, fakeFirstUserEntity)
    }

    @Test
    fun `convert user entity to domain correctly`() {
        val convertedFirstUserEntityToDomain = fakeFirstUserEntity.toDomain()

        Assert.assertEquals(convertedFirstUserEntityToDomain, fakeFirstUser)
    }

    @Test
    fun `convert user entity list to domain list correctly`() {
        val convertedUserEntityListToDomain = fakeUserEntityList.toDomain()

        Assert.assertEquals(convertedUserEntityListToDomain, fakeUserDomainList)
    }

}