package com.example.formgim.presentation.main.profile

import MyTopAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.appgim.domain.auth.models.UserModel
import com.example.formgim.ui.theme.Constants


@Composable
fun ProfileScreen(
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel(),
    logout: () -> Unit = {},
) {
    val settingState by profileScreenViewModel.settingState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyTopAppBar(
                title = "Perfil",
                backEvent = { },
                actions = {
                    IconButton(
                        onClick = { logout() },
                        modifier = Modifier.padding(end = Constants.PaddingSizes.M.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "Logout",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                showBackEvent = false,
            )
        }

    ) { contentPadding ->
        val user = settingState.user
        if (settingState.isLoading || user == null) {
            Text(
                text = "Loading...",
                modifier = Modifier.padding(contentPadding),
                style = typography.bodyLarge
            )
            return@Scaffold
        }

        UserProfileCard(
            user = user,
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .fillMaxSize()
                .padding(Constants.PaddingSizes.M.dp)
        )

    }

}


@Composable
fun UserProfileCard(user: UserModel, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(Constants.Dimensions.CARD_ELEVATION.dp),
        shape = RoundedCornerShape(Constants.Dimensions.CARD_RADIUS.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Constants.PaddingSizes.XXL.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = if (user.pictureUrl.isNotEmpty()) {
                    rememberAsyncImagePainter(model = user.pictureUrl)
                } else {
                    rememberAsyncImagePainter(model = Icons.Default.Person)
                },
                contentDescription = "User Profile Image",
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .border(2.dp, colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.height(Constants.PaddingSizes.M.dp))

            Text(
                text = "Nombre",
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            Text(
                text = "${user.name} ${user.surname}",
                style = typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Email",
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            Text(
                text = user.email,
                style = typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Rol:",
                style = typography.titleMedium,
                color = colorScheme.primary
            )
            Text(
                text = if (user.isAdmin) "Administrador" else "Usuario",
                style = typography.bodyLarge
            )
        }
    }
}

