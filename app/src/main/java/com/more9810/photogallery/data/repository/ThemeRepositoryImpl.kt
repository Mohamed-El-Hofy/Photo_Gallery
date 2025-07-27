package com.more9810.photogallery.data.repository


import android.content.Context
import com.more9810.photogallery.data.local.systemData.ThemeHelper
import com.more9810.photogallery.domain.repository.ThemeRepository
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val context: Context
) : ThemeRepository {

    override fun applyTheme() {
        ThemeHelper.applyTheme(context)
    }

    override fun toggleTheme() {
        ThemeHelper.toggleTheme(context)
    }

    override fun isNightMode(): Boolean {
        return ThemeHelper.isNightMode(context)
    }
}
