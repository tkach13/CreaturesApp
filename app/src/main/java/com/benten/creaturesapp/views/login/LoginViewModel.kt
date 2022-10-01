package com.benten.creaturesapp.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val goToAllCreaturesSharedFlow = MutableSharedFlow<Boolean>()
    fun getAllCreaturesSharedFlow(): SharedFlow<Boolean> {
        return goToAllCreaturesSharedFlow
    }

    fun onLoginIntent() {
        viewModelScope.launch {
            delay(3000)
            goToAllCreaturesSharedFlow.emit(true)
        }
    }
}