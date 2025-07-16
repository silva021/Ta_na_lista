package com.silva021.tanalista.ui.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.tanalista.util.helper.PreferencesManager
import kotlinx.coroutines.launch

class WelcomeViewModel(
    val preferencesManager: PreferencesManager,
) : ViewModel() {

    fun setFlagShowWelcomeScreen() {
        viewModelScope.launch {
            preferencesManager.setWelcomeShown(true)
        }
    }
}