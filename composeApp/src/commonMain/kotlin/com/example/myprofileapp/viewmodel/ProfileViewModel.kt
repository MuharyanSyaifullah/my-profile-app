package com.example.myprofileapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myprofileapp.data.Profile
import com.example.myprofileapp.data.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ProfileUiState(
    val profile: Profile = SampleData.profile,
    val isDarkMode: Boolean = false,
    val isEditMode: Boolean = false
)

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleDarkMode(isDark: Boolean) {
        _uiState.update { it.copy(isDarkMode = isDark) }
    }

    fun setEditMode(isEdit: Boolean) {
        _uiState.update { it.copy(isEditMode = isEdit) }
    }

    fun updateProfile(
        name: String,
        bio: String,
        email: String,
        phone: String,
        location: String
    ) {
        _uiState.update { 
            it.copy(
                profile = it.profile.copy(
                    name = name,
                    bio = bio,
                    email = email,
                    phone = phone,
                    location = location
                ),
                isEditMode = false
            )
        }
    }
}
