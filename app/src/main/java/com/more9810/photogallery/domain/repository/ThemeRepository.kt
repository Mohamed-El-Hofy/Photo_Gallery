package com.more9810.photogallery.domain.repository

interface ThemeRepository {
    fun applyTheme()
    fun toggleTheme()
    fun isNightMode(): Boolean
}