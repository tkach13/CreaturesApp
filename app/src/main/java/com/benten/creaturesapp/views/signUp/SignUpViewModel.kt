package com.benten.creaturesapp.views.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.creaturesapp.model.User
import com.benten.creaturesapp.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val signUpEventFlow = MutableSharedFlow<Boolean>()
    fun getSignUpEventFlow(): SharedFlow<Boolean> {
        return signUpEventFlow
    }

    fun onSignup(email: String, password: String, fullName: String) {
        userRepository.createUser(User(email, password)) { result ->
            if (result) {
                val userId = userRepository.getUser()?.uid
                userRepository.saveUser(User(eMail = email, userid = userId, fullName = fullName)) {
                }
            }
            viewModelScope.launch {
                signUpEventFlow.emit(result)
            }
        }
    }

}