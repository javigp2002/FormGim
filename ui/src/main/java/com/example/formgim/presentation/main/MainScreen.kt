package com.example.formgim.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.formgim.presentation.main.navigation.MainNavigationScreenNames
import com.example.formgim.presentation.main.navigation.components.MainNavHostController
import com.example.formgim.presentation.main.navigation.components.MyBottomNavBar
import com.example.formgim.presentation.main.navigation.currentRoute

@Composable
fun MainScreen(
    logout: () -> Unit = {},
    mainScreenViewmodel: MainScreenViewmodel = hiltViewModel()
) {
    val isAdminState = mainScreenViewmodel.isAdmin.collectAsState()
    val navController = rememberNavController()
    val currentDestination = currentRoute(navController)

    LaunchedEffect(Unit) {
        mainScreenViewmodel.checkUser()
    }

    val showBottomBarInSelectedDestinations = when (currentDestination) {
        MainNavigationScreenNames.HomeScreen.name, MainNavigationScreenNames.Settings.name -> true
        else -> false
    }



    Scaffold(
        bottomBar = {
            if (showBottomBarInSelectedDestinations) {
                    MyBottomNavBar(navController)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            MainNavHostController(navController, logout)
        }
    }
}
