package com.more9810.photogallery.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.more9810.photogallery.domain.useCase.FetchDataUseCase
import com.more9810.photogallery.domain.useCase.GetNetworkStateUseCase
import com.more9810.photogallery.presentation.uiState.PhotoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PexelsViewModel @Inject constructor(
    private val fetchDataUseCase: FetchDataUseCase,
    private val getNetworkStateUseCase: GetNetworkStateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getPhoto()
        checkNet()
    }




    fun checkNet() {
        viewModelScope.launch {
            getNetworkStateUseCase.invoke().collect {
                _uiState.update { uiState ->
                    uiState.copy(isNetConnect = it)
                }
            }
        }
    }

    fun getPhoto() {
        viewModelScope.launch {
            fetchDataUseCase(70).collect { resource ->
                _uiState.update { uiState ->
                    uiState.copy(itemList = resource)
                }
            }
        }
    }

    fun onClackTry() {
        getPhoto()
    }
}