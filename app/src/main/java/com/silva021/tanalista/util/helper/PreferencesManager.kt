package com.silva021.tanalista.util.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesManager(private val context: Context) {

    companion object {
        private val WELCOME_SHOWN_KEY = booleanPreferencesKey("welcome_shown")
    }

    suspend fun setWelcomeShown(shown: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[WELCOME_SHOWN_KEY] = shown
        }
    }

    val isWelcomeShown: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[WELCOME_SHOWN_KEY] ?: false
        }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
