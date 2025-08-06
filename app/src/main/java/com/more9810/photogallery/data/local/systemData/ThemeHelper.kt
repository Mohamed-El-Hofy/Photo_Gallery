package com.more9810.photogallery.data.local.systemData

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

object ThemeHelper {

    private const val PREF_NAME = "theme_pref"
    private const val PREF_THEME_MODE = "pref_theme_mode"

    /** تطبيق الثيم المحفوظ */
    fun applyTheme(context: Context) {
        val mode = getSavedThemeMode(context)
        AppCompatDelegate.setDefaultNightMode(mode)

    }

    /** تبديل الوضع */
    fun toggleTheme(context: Context) {
        val currentMode = getSavedThemeMode(context)
        val newMode = if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        saveThemeMode(context, newMode)
        AppCompatDelegate.setDefaultNightMode(newMode)
    }

    /** هل الوضع الحالي Night Mode؟ */
    fun isNightMode(context: Context): Boolean {
        return getSavedThemeMode(context) == AppCompatDelegate.MODE_NIGHT_YES
    }

    /** حفظ الثيم */
    private fun saveThemeMode(context: Context, mode: Int) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit {
            putInt(PREF_THEME_MODE, mode)
        }
    }

    /** استرجاع الثيم */
    private fun getSavedThemeMode(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getInt(PREF_THEME_MODE, AppCompatDelegate.MODE_NIGHT_NO)
    }
}
