package com.silva021.tanalista.util.helper

import android.content.Context
import androidx.core.content.edit

class PreferencesManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_WELCOME_SHOWN = "welcome_shown"
    }

    fun setWelcomeShown(shown: Boolean) {
        prefs.edit { putBoolean(KEY_WELCOME_SHOWN, shown) }
    }

    fun isWelcomeShown(): Boolean {
        return prefs.getBoolean(KEY_WELCOME_SHOWN, false)
    }
}
