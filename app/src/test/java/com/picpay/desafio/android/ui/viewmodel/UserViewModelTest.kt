package com.picpay.desafio.android.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.utils.*
import com.picpay.desafio.android.utils.datasource.FakeFavoriteDataSource
import com.picpay.desafio.android.utils.datasource.FakeUserDataSource
import com.slailati.android.domain.repository.UserRepository
import com.slailati.android.domain.repository.UserRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var userViewModel: UserViewModel
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(
            FakeUserDataSource(
                mutableListOf(
                    fakeFirstUser,
                    fakeSecondUser,
                    fakeThirdUser
                )
            ), FakeFavoriteDataSource()
        )
        userViewModel = UserViewModel(userRepository)
    }

    @Test
    fun `insert favorite to database`() {
        userViewModel.saveFavorite(fakeFavoriteFirstUser)
        userViewModel.getFavorites()

        val result = userViewModel.favorites().getOrAwaitValue()

        Assert.assertEquals(result.size, 1)
    }

    @Test
    fun `delete favorite from database`() {
        userViewModel.saveFavorite(fakeFavoriteFirstUser)
        userViewModel.removeFavorite(fakeFavoriteFirstUser.id)

        userViewModel.getFavorites()
        val result = userViewModel.favorites().getOrAwaitValue()

        Assert.assertEquals(result.size, 0)
    }

    @Test
    fun `get users from server to fill the contacts list`() {
        userViewModel.getContacts()
        val result = userViewModel.users().getOrAwaitValue()

        Assert.assertEquals(result.size, 3)
    }

    @Test
    fun `check if a user from server is favorite`() {
        userViewModel.saveFavorite(fakeFavoriteFirstUser)

        userViewModel.getContacts()
        val result = userViewModel.users().getOrAwaitValue()

        val isFirstUserFavorite = result.find { it.id == fakeFavoriteFirstUser.id }?.isFavorite

        Assert.assertEquals(isFirstUserFavorite, true)
    }

    @Test
    fun `check if a user from server is not favorite`() {
        userViewModel.saveFavorite(fakeFavoriteFirstUser)

        userViewModel.getContacts()
        val result = userViewModel.users().getOrAwaitValue()

        val isSecondUserFavorite = result.find { it.id != fakeFavoriteFirstUser.id }?.isFavorite

        Assert.assertEquals(isSecondUserFavorite, false)
    }

    @Test
    fun `get empty favorites from database to fill the favorites list`() {
        userViewModel.getFavorites()
        val result = userViewModel.favorites().getOrAwaitValue()

        Assert.assertTrue(result.isEmpty())
    }

    @Test
    fun `get not empty favorites from database to fill the favorites list`(){
        userViewModel.saveFavorite(fakeFavoriteFirstUser)
        userViewModel.getFavorites()
        val result = userViewModel.favorites().getOrAwaitValue()

        Assert.assertTrue(result.isNotEmpty())
    }

}