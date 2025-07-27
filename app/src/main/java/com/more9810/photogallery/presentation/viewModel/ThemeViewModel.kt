package com.more9810.photogallery.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.more9810.photogallery.domain.useCase.GetThemeModeUseCase
import com.more9810.photogallery.domain.useCase.ToggleThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val toggleThemeUseCase: ToggleThemeUseCase
) : ViewModel() {



    // LiveData تمثل الوضع الحالي
    private val _isNightMode = MutableLiveData<Boolean>()
    val isNightMode: LiveData<Boolean> get() = _isNightMode

    init {
        _isNightMode.value = getThemeModeUseCase()
    }

    // استدعاء التغيير
    fun toggleTheme() {
        toggleThemeUseCase()
        _isNightMode.value = getThemeModeUseCase()
    }
}
