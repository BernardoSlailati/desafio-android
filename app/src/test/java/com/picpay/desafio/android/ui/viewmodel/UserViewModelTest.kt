package com.picpay.desafio.android.ui.viewmodel

import com.slailati.android.domain.model.User
import com.slailati.android.domain.repository.UserRepository
import com.slailati.android.domain.repository.UserRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserViewModelTest {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userRepository: UserRepository

    private val fakeInsertedUser = User("img1", "name1", 1, "username1")
    private val fakeInsertedUserId = 1

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(FakeUserDataSource(), FakeFavoriteDataSource())
        userViewModel = UserViewModel(userRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insert favorite to database`() = runTest {
        userViewModel.saveFavorite(fakeInsertedUser)
        val favoritesSize = userRepository.getFavorites().size

        Assert.assertEquals(favoritesSize, 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `delete favorite from database`() = runTest {
        userViewModel.saveFavorite(fakeInsertedUser)
        userViewModel.removeFavorite(1)
        val favoritesSize = userRepository.getFavorites().size

        Assert.assertEquals(favoritesSize, 0)
    }

}