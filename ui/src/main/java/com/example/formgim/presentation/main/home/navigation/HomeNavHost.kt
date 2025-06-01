package com.example.formgim.presentation.main.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.formgim.presentation.main.home.tabs.done_form.DoneForm
import com.example.formgim.presentation.main.home.tabs.my_form.MyFormsTab
import com.example.formgim.presentation.main.home.tabs.new_form.NewFormsTab

@Composable
fun HomeTabHost(
    navController: NavHostController,
    startDestination: HomeTab,
    isAdmin: Boolean,
    goToDetail: (formId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        HomeTab.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    HomeTab.NewForms -> NewFormsTab(
                        goToDetail = goToDetail,
                        modifier = Modifier.fillMaxSize()
                    )

                    HomeTab.MyForms -> MyFormsTab(
                        goToDetail = goToDetail,
                        modifier = modifier.fillMaxSize()
                    )

                    HomeTab.DoneForms -> if (isAdmin)
                        DoneForm(
                            goToDetail = goToDetail,
                            modifier = modifier.fillMaxSize()
                        )
                    else {
                        NewFormsTab(modifier = Modifier.fillMaxSize()) // en caso de que falle
                    }
                }
            }
        }
    }
}