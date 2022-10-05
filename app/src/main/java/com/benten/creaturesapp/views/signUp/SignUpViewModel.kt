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
class SignUpViewModel @Inject constructor() :
    ViewModel() {

    private val signUpEventFlow = MutableSharedFlow<Boolean>()
    fun getSignUpEventFlow(): SharedFlow<Boolean> {
        return signUpEventFlow
    }

    fun onSignup(string: String, toString: String) {


    }

}