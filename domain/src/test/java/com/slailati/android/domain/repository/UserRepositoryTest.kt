package com.slailati.android.domain.repository

import com.slailati.android.domain.utils.datasource.FakeFavoriteDataSource
import com.slailati.android.domain.utils.datasource.FakeUserDataSource
import com.slailati.android.domain.utils.fakeFavoriteUserToBeDeleted
import com.slailati.android.domain.utils.fakeFavoriteUserToBeInserted
import com.slailati.android.domain.utils.fakeFavoritesUserEntityList
import com.slailati.android.domain.utils.fakeUserDomainListAllCorrect
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class UserRepositoryTest {

    private lateinit var fakeUserDataSource: FakeUserDataSource
    private lateinit var fakeFavoriteDataSource: FakeFavoriteDataSource

    private lateinit var userRepository: UserRepository

    @Before
    fun createRepository() {
        fakeUserDataSource = FakeUserDataSource()
        fakeFavoriteDataSource = FakeFavoriteDataSource(fakeFavoritesUserEntityList.toMutableList())
        userRepository = UserRepositoryImpl(
            fakeUserDataSource, fakeFavoriteDataSource
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get contacts from server correctly`() = runTest {
        val contacts = userRepository.getContacts()

        assertEquals(contacts, fakeUserDomainListAllCorrect)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get favorites from database correctly`() = runTest {
        val favorites = userRepository.getFavorites()

        assertEquals(favorites, fakeFavoritesUserEntityList)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert favorite to database correctly`() = runTest {
        userRepository.saveFavorite(fakeFavoriteUserToBeInserted)

        assertEquals(fakeFavoriteDataSource.favoriteUsers.size, 4)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete favorite from database correctly`() = runTest {
        userRepository.removeFavorite(fakeFavoriteUserToBeDeleted.id)

        assertEquals(fakeFavoriteDataSource.favoriteUsers.size, 2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete favorite from database wrong`() = runTest {
        userRepository.removeFavorite(fakeFavoriteUserToBeInserted.id)

        assertEquals(fakeFavoriteDataSource.favoriteUsers.size, 3)
    }

}