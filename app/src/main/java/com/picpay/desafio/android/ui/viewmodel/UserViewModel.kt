package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slailati.android.domain.model.User
import com.slailati.android.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class UserViewModel(private val userRepository: UserRepository) : ViewModel(), LifecycleObserver,
    KoinComponent {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    fun users() = _users

    private val _favorites: MutableLiveData<List<User>> = MutableLiveData()
    fun favorites() = _favorites

    fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites =
                withContext(Dispatchers.IO) { userRepository.getFavorites() }
            val users =
                withContext(Dispatchers.IO) { userRepository.getContacts() }
            users.map { user ->
                if (favorites.any { favorite -> favorite.id == user.id }) {
                    user.isFavorite = true
                }
            }
            _users.postValue(users)
        }
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites =
                withContext(Dispatchers.IO) { userRepository.getFavorites() }
            _favorites.postValue(favorites)
        }
    }

    fun saveFavorite(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.saveFavorite(user)
        }
    }

    fun removeFavorite(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.removeFavorite(userId)
        }
    }

}