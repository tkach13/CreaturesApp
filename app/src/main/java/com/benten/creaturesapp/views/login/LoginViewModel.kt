package com.benten.creaturesapp.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.creaturesapp.model.User
import com.benten.creaturesapp.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private val goToAllCreaturesSharedFlow = MutableSharedFlow<Boolean>()
    fun getAllCreaturesSharedFlow(): SharedFlow<Boolean> {
        return goToAllCreaturesSharedFlow
    }

    fun onLoginIntent(user: String, pw: String) {
        userRepository.logIn(User(user, pw)) { result ->
            viewModelScope.launch {
                goToAllCreaturesSharedFlow.emit(result)
            }
        }
    }
}