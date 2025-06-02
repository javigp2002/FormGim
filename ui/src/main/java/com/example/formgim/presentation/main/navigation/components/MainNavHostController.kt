package com.example.formgim.presentation.main.navigation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.formgim.presentation.main.home.HomeScreen
import com.example.formgim.presentation.main.home.admin.creation_form.CreationFormScreen
import com.example.formgim.presentation.main.home.admin.form_stats.DataFormScreen
import com.example.formgim.presentation.main.home.answered_form_screen.DoneFormScreen
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
                },
                goToFormStats = { formId ->
                    navController.navigate(
                        "${MainNavigationScreenNames.StatsForm.name}/$formId"
                    ) {}
                },
                goToDoneForm = { formId ->
                    navController.navigate(
                        "${MainNavigationScreenNames.DoneForm.name}/$formId"
                    ) {}
                },
                goToCreationForm = {
                    navController.navigate(MainNavigationScreenNames.CreationForm.name) {
                        popUpTo(MainNavigationScreenNames.HomeScreen.name) { inclusive = false }
                    }
                },
            )
        }
        composable(
            route = "${MainNavigationScreenNames.Detail.name}/{formId}",
            arguments = listOf(navArgument("formId") {
                type = androidx.navigation.NavType.IntType
            })
        ) { backStackEntry ->
            val formId = backStackEntry.arguments?.getInt("formId") ?: -1

            FormToFillScreen(
                formId = formId,
                goBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${MainNavigationScreenNames.DoneForm.name}/{formId}",
            arguments = listOf(navArgument("formId") {
                type = androidx.navigation.NavType.IntType
            })
        ) { backStackEntry ->
            val formId = backStackEntry.arguments?.getInt("formId") ?: -1

            DoneFormScreen(
                formId,
                goBack = { navController.popBackStack() })
        }

        composable(
            route = "${MainNavigationScreenNames.StatsForm.name}/{formId}",
            arguments = listOf(navArgument("formId") {
                type = androidx.navigation.NavType.IntType
            })
        ) { backStackEntry ->
            val formId = backStackEntry.arguments?.getInt("formId") ?: -1
            DataFormScreen(
                onBack = { navController.popBackStack() },
                formId = formId,
            )
        }

        composable(MainNavigationScreenNames.CreationForm.name) {
            CreationFormScreen(
                onBack = { navController.popBackStack() },
            )
        }

        composable(MainNavigationScreenNames.Settings.name) {
            SettingsScreen()
        }
    }
}

@Composable fun SettingsScreen() { Text("Profile") }