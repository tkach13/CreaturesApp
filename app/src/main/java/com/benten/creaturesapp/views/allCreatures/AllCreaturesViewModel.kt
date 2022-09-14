package com.benten.creaturesapp.views.allCreatures


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benten.creaturesapp.di.DataModule
import com.benten.creaturesapp.model.CreaturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCreaturesViewModel @Inject constructor( private val creaturesRepository: CreaturesRepository, ) :
    ViewModel() {


    private val _addClicked = MutableSharedFlow<Boolean>()

    val addClikedSharedFlow = _addClicked
    private val _uiState =
        MutableStateFlow<AllCreaturesState>(AllCreaturesState.Success(emptyList()))
    val uiState: StateFlow<AllCreaturesState> = _uiState

    init {
        viewModelScope.launch {
            creaturesRepository.getAllCreatures()
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
            creaturesRepository.searchCreatures(query)
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