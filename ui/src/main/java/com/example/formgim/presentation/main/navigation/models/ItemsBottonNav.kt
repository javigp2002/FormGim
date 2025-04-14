package com.example.formgim.presentation.main.navigation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.formgim.presentation.main.navigation.MainNavigationScreenNames

sealed class ItemsBottonNav(
    val icon: ImageVector,
    val title: String,
    val ruta: String
){
    object ItemBottonNav1: ItemsBottonNav(
        Icons.Outlined.Home,
        "Home",
        MainNavigationScreenNames.HomeScreen.name
    )
    object ItemBottonNav2: ItemsBottonNav(
        Icons.Outlined.AccountCircle,
        "Settings",
        MainNavigationScreenNames.Settings.name
    )
}