package com.more9810.photogallery.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.more9810.photogallery.data.remote.api.service.PexelsApiService
import com.more9810.photogallery.data.repository.PhotoRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PexelsViewModel @Inject constructor(
    val api: PexelsApiService,
    val repo : PhotoRepositoryImpl
): ViewModel() {



     fun getPhoto() {
        viewModelScope.launch {
            repo.getPhoto().collect {
                Log.e("TAG_getPhoto", "getPhotoViewModel:${it} ", )
            }
        }
    }
}