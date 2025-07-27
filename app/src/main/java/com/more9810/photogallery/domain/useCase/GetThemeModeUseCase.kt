package com.more9810.photogallery.domain.useCase

import com.more9810.photogallery.domain.repository.ThemeRepository

class GetThemeModeUseCase(private val repository: ThemeRepository) {
    operator fun invoke(): Boolean = repository.isNightMode()
}