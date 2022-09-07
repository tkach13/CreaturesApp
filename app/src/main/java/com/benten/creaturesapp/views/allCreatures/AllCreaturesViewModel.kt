package com.benten.creaturesapp.views.allCreatures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.model.room.CreaturesRepositoryImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllCreaturesViewModel : ViewModel() {
    private val creaturesRepo = CreaturesRepositoryImpl()

    private val _addClicked = MutableSharedFlow<Boolean>()

    val addClikedSharedFlow = _addClicked
    private val _uiState =
        MutableStateFlow<AllCreaturesState>(AllCreaturesState.Success(emptyList()))
    val uiState: StateFlow<AllCreaturesState> = _uiState

    init {
        viewModelScope.launch {
            creaturesRepo.getAllCreatures()
                .catch { exception: Throwable ->
                    _uiState.value = AllCreaturesState.Error(exception)
                }
                .collect {
                    _uiState.value = AllCreaturesState.Success(it)
                }
        }
    }


    fun onSearchRequested(query: String) {

        viewModelScope.launch {
            creaturesRepo.searchCreatures(query)
                .collect {
                    _uiState.value = AllCreaturesState.Success(it)
                }
        }
    }

    fun onPlusClicked() {
        viewModelScope.launch {
            _addClicked.emit(true)
        }
    }

}