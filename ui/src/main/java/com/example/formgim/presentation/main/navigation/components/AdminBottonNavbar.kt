package com.example.formgim.presentation.main.navigation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.formgim.presentation.main.navigation.currentRoute
import com.example.formgim.presentation.main.navigation.models.ItemsBottonNav

@Composable
fun AdminBottonNavBar(
    navHostController: NavHostController
) {
    val menuItems = listOf(
        ItemsBottonNav.ItemBottonNav1,
    )
    BottomAppBar {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ) {
            menuItems.forEach { item ->
                val selectedItem = currentRoute(navHostController) == item.ruta
                NavigationBarItem(
                    selected = selectedItem,
                    onClick = {
                        navHostController.navigate(item.ruta) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}