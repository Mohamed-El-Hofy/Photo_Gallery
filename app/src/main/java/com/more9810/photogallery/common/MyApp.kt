package com.more9810.photogallery.common

import android.app.Application
import com.more9810.photogallery.data.local.systemData.ThemeHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        ThemeHelper.applyTheme(this)
        super.onCreate()
    }
}