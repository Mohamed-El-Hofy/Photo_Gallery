package com.more9810.photogallery.domain.state

import com.more9810.photogallery.domain.model.Photo

sealed class PhotoUiState {
    object Loading : PhotoUiState()
    data class Success(val photos: List<Photo>) : PhotoUiState()
    data class Error(val message: String) : PhotoUiState()
}