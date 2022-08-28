package com.slailati.android.domain.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.slailati.android.data.local.database.MainDatabase
import com.slailati.android.data.local.database.dao.FavoriteContactsDao
import com.slailati.android.domain.mapper.*
import com.slailati.android.domain.repository.datasource.FakeFavoriteDataSource
import com.slailati.android.domain.repository.datasource.FakeUserDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException


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