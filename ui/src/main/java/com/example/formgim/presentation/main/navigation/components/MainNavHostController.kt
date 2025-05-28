package com.example.formgim.presentation.main.navigation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.formgim.presentation.main.home.HomeScreen
import com.example.formgim.presentation.main.home.form_to_fill.FormToFillScreen
import com.example.formgim.presentation.main.navigation.MainNavigationScreenNames

@Composable
fun MainNavHostController(
    navController: NavHostController,
    logout: () -> Unit = {}
) {
    NavHost(navController, startDestination = "HomeScreen") {
        composable(MainNavigationScreenNames.HomeScreen.name) {
            HomeScreen(
                goToDetail = { formId ->
                    navController.navigate(
                        "${MainNavigationScreenNames.Detail.name}/$formId"
                    ) {}
                }
            )
        }
        composable(
            route = "${MainNavigationScreenNames.Detail.name}/{formId}",
            arguments = listOf(navArgument("formId") {
                type = androidx.navigation.NavType.IntType
            })
        ) { backStackEntry ->
            val formId = backStackEntry.arguments?.getInt("formId") ?: -1

            FormToFillScreen(formId = formId)
        }

        composable(MainNavigationScreenNames.Settings.name) {
            SettingsScreen()
        }
    }
}

@Composable fun SettingsScreen() { Text("Profile") }