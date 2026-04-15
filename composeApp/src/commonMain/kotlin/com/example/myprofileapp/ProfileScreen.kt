package com.example.myprofileapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.myprofileapp.components.InfoItem
import com.example.myprofileapp.components.ProfileCard
import com.example.myprofileapp.components.ProfileHeader

@Composable
fun ProfileScreen(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader(
            name = profile.name,
            role = profile.role
        )

        ProfileCard(
            bio = profile.bio
        )

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Contact Information",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            InfoItem(
                icon = Icons.Default.Email,
                label = "Email",
                value = profile.email
            )

            InfoItem(
                icon = Icons.Default.Phone,
                label = "Phone",
                value = profile.phone
            )

            InfoItem(
                icon = Icons.Default.LocationOn,
                label = "Location",
                value = profile.location
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { 
                uriHandler.openUri("mailto:${profile.email}")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Contact Me")
        }
    }
}
