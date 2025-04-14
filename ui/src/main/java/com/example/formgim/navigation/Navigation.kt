package com.example.formgim.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.formgim.presentation.main.MainScreen
import com.example.formgim.presentation.auth.LoginScreen
import com.example.formgim.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = GeneralNavigationScreenNames.Splash.name
    ){
        composable(GeneralNavigationScreenNames.Splash.name){
            SplashScreen(
                onClickGoToLogin = {
                    navController.navigate(GeneralNavigationScreenNames.Login.name){
                        popUpTo(GeneralNavigationScreenNames.Splash.name) { inclusive = true }
                    }
                },
            )
        }

        composable(GeneralNavigationScreenNames.Login.name){
            LoginScreen(onSuccessGoToMain = {
                navController.navigate(GeneralNavigationScreenNames.Main.name) {
                    popUpTo(GeneralNavigationScreenNames.Login.name) { inclusive = true }
                }
            })
        }

        composable(GeneralNavigationScreenNames.Main.name){
            MainScreen()
        }
    }
}