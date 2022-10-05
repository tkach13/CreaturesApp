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
    private val creaturesRepository: CreaturesRepository,
) :
    ViewModel() {


    private val _addClicked = MutableSharedFlow<Boolean>()

    val addClikedSharedFlow = _addClicked
    private val _uiState =
        MutableStateFlow<AllCreaturesState>(AllCreaturesState.Success(emptyList()))
    val uiState: StateFlow<AllCreaturesState> = _uiState

    init {

    }


    fun onSearchRequested(query: String) {

        viewModelScope.launch {

        }
    }

    fun onPlusClicked() {
        viewModelScope.launch {
            _addClicked.emit(true)
        }
    }

}