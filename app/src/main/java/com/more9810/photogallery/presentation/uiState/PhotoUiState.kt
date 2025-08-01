package com.more9810.photogallery.presentation.uiState

import com.more9810.photogallery.data.remote.api.state.Resource
import com.more9810.photogallery.domain.model.Photo

data class PhotoUiState (
    val isNight : Boolean = false,
    val title: String= "",
    val itemList: Resource<List<Photo>>? = null,
    val isNetConnect: Boolean = false,
    val isLoading: Boolean= false,
    val isSuccess: Boolean= false,
    val splashScreenIsLoading: Boolean = true
)
