package com.example.myprofileapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.myprofileapp.ui.components.InfoItem
import com.example.myprofileapp.ui.components.ProfileCard
import com.example.myprofileapp.ui.components.ProfileHeader
import com.example.myprofileapp.viewmodel.ProfileUiState
import com.example.myprofileapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (uiState.isDarkMode) "Dark Mode" else "Light Mode",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(end = 8.dp)
            )
            Switch(
                checked = uiState.isDarkMode,
                onCheckedChange = { viewModel.toggleDarkMode(it) }
            )
        }

        if (uiState.isEditMode) {
            EditProfileForm(
                initialName = uiState.profile.name,
                initialBio = uiState.profile.bio,
                initialEmail = uiState.profile.email,
                initialPhone = uiState.profile.phone,
                initialLocation = uiState.profile.location,
                onSave = { name, bio, email, phone, location -> 
                    viewModel.updateProfile(name, bio, email, phone, location) 
                },
                onCancel = { viewModel.setEditMode(false) }
            )
        } else {
            ProfileView(
                uiState = uiState,
                onEditClick = { viewModel.setEditMode(true) },
                onContactClick = { uriHandler.openUri("mailto:${uiState.profile.email}") }
            )
        }
    }
}

@Composable
fun ProfileView(
    uiState: ProfileUiState,
    onEditClick: () -> Unit,
    onContactClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(48.dp))
            ProfileHeader(
                name = uiState.profile.name,
                role = uiState.profile.role
            )
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
            }
        }

        ProfileCard(bio = uiState.profile.bio)

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Contact Information",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            InfoItem(icon = Icons.Default.Email, label = "Email", value = uiState.profile.email)
            InfoItem(icon = Icons.Default.Phone, label = "Phone", value = uiState.profile.phone)
            InfoItem(icon = Icons.Default.LocationOn, label = "Location", value = uiState.profile.location)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onContactClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Contact Me")
        }
    }
}

@Composable
fun EditProfileForm(
    initialName: String,
    initialBio: String,
    initialEmail: String,
    initialPhone: String,
    initialLocation: String,
    onSave: (String, String, String, String, String) -> Unit,
    onCancel: () -> Unit
) {
    var editedName by remember { mutableStateOf(initialName) }
    var editedBio by remember { mutableStateOf(initialBio) }
    var editedEmail by remember { mutableStateOf(initialEmail) }
    var editedPhone by remember { mutableStateOf(initialPhone) }
    var editedLocation by remember { mutableStateOf(initialLocation) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Edit Profile", style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = onCancel) {
                Icon(Icons.Default.Close, contentDescription = "Cancel")
            }
        }

        OutlinedTextField(
            value = editedName,
            onValueChange = { editedName = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = editedEmail,
            onValueChange = { editedEmail = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = editedPhone,
            onValueChange = { editedPhone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = editedLocation,
            onValueChange = { editedLocation = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = editedBio,
            onValueChange = { editedBio = it },
            label = { Text("Bio") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Button(
            onClick = { onSave(editedName, editedBio, editedEmail, editedPhone, editedLocation) },
            modifier = Modifier.fillMaxWidth(),
            enabled = editedName.isNotBlank() && editedBio.isNotBlank() && editedEmail.isNotBlank()
        ) {
            Text("Save Changes")
        }
    }
}
