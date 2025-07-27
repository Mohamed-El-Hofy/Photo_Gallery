package com.more9810.photogallery.domain.useCase


import com.more9810.photogallery.domain.repository.ThemeRepository


class ToggleThemeUseCase(private val repository: ThemeRepository) {
    operator fun invoke() = repository.toggleTheme()
}