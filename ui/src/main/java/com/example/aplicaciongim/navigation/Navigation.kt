package com.example.formgim.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.formgim.presentation.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = NavigationScreenNames.HomeScreen.name
    ){
        composable(NavigationScreenNames.HomeScreen.name){
            HomeScreen()
        }
    }
}