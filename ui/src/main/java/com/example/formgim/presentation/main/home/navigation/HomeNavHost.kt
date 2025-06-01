package com.example.formgim.presentation.main.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.formgim.presentation.main.home.tabs.MyFormsTab
import com.example.formgim.presentation.main.home.tabs.NewFormsTab

@Composable
fun HomeTabHost(
    navController: NavHostController,
    startDestination: HomeTab,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        HomeTab.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    HomeTab.NewForms -> NewFormsTab(modifier = Modifier.fillMaxSize())
                    HomeTab.MyForms -> MyFormsTab(modifier = modifier.fillMaxSize())
                }
            }
        }
    }
}