package com.example.formgim.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.formgim.navigation.NavigationScreenNames

sealed class ItemsBottonNav(
    val icon: ImageVector,
    val title: String,
    val ruta: String
){
    object ItemBottonNav1: ItemsBottonNav(
        Icons.Outlined.Home,
        "Home",
        NavigationScreenNames.HomeScreen.name
    )
    object ItemBottonNav2: ItemsBottonNav(
        Icons.AutoMirrored.Outlined.Login,
        "Login",
        NavigationScreenNames.Login.name
    )
}