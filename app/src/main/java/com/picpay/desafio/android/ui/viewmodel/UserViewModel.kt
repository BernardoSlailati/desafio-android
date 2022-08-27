package com.picpay.desafio.android.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel(), LifecycleObserver, KoinComponent {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    fun users() = _users

    fun getContacts() {
        viewModelScope.launch {
            val users =  async { userRepository.getContacts() }
            _users.postValue(users.await())
        }
    }

}