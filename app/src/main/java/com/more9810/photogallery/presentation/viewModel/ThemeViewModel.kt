package com.more9810.photogallery.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.more9810.photogallery.domain.useCase.GetThemeModeUseCase
import com.more9810.photogallery.domain.useCase.ToggleThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val toggleThemeUseCase: ToggleThemeUseCase
) : ViewModel() {

    private val _isNightMode = MutableLiveData<Boolean>()
    val isNightMode: LiveData<Boolean> get() = _isNightMode
    private val _splashScreenState: MutableLiveData<Boolean> = MutableLiveData(true)
    val splashScreenState: LiveData<Boolean> get() = _splashScreenState


    init {
        _isNightMode.value = getThemeModeUseCase()
        startSplash()
    }

    // استدعاء التغيير
    fun toggleTheme() {
        toggleThemeUseCase()
        _isNightMode.value = getThemeModeUseCase()
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(1000)
            _splashScreenState.value = false

        }
    }
}
