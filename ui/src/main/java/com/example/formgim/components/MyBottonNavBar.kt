package com.example.formgim.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.formgim.models.ItemsBottonNav
import com.example.formgim.navigation.currentRoute

@Composable
fun MyBottomNavBar(
    navHostController: NavHostController
){
    val menuItems = listOf(
        ItemsBottonNav.ItemBottonNav1,
        ItemsBottonNav.ItemBottonNav2

    )
    BottomAppBar{
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ){
            menuItems.forEach { item ->
                val selectedItem = currentRoute(navHostController) == item.ruta
                NavigationBarItem(
                    selected = selectedItem ,
                    onClick = {
                        navHostController.navigate(item.ruta)
                    },
                    icon = {
                        Icon(imageVector = item.icon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}