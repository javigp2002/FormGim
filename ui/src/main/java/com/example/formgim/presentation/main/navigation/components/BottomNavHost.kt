package com.example.formgim.presentation.main.navigation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.formgim.presentation.main.home.HomeScreen
import com.example.formgim.presentation.main.home.detail.DetailScreen
import com.example.formgim.presentation.main.navigation.MainNavigationScreenNames

@Composable
fun BottomNavHost(navController: NavHostController,
                  logout: ()->Unit = {}) {
    NavHost(navController, startDestination = "HomeScreen") {
        composable(MainNavigationScreenNames.HomeScreen.name) {
            HomeScreen(
                goToDetail = { navController.navigate(MainNavigationScreenNames.Detail.name) {} }
            )
        }
        composable(MainNavigationScreenNames.Detail.name) {
            DetailScreen()
        }

        composable(MainNavigationScreenNames.Settings.name) {
            SettingsScreen()
        }
    }
}

@Composable fun SettingsScreen() { Text("Profile") }