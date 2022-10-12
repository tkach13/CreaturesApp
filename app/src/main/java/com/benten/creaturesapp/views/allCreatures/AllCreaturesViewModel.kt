package com.benten.creaturesapp.views.allCreatures


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.creaturesapp.di.DataModule
import com.benten.creaturesapp.model.CreaturesRepository
import com.benten.creaturesapp.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCreaturesViewModel @Inject constructor(
    creaturesRepository: CreaturesRepository,
    userRepository: UserRepository
) :
    ViewModel() {


    private val _addClicked = MutableSharedFlow<Boolean>()

    val addClikedSharedFlow = _addClicked
    private val _uiState =
        MutableStateFlow<AllCreaturesState>(AllCreaturesState.Success(emptyList()))
    val uiState: StateFlow<AllCreaturesState> = _uiState

    init {
        creaturesRepository.getAllCreatures(userRepository.getUser()?.uid ?: "") { creatures ->
            viewModelScope.launch {
                _uiState.emit(AllCreaturesState.Success(creatures))
            }

        }
    }
    fun onPlusClicked() {
        viewModelScope.launch {
            _addClicked.emit(true)
        }
    }
}