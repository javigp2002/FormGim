package com.example.formgim.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.formgim.presentation.main.navigation.components.BottomNavHost
import com.example.formgim.presentation.main.navigation.components.MyBottomNavBar

@Composable
fun MainScreen (logout: ()->Unit = {}){

    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            MyBottomNavBar(navController)
        }
    ){ padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ){
            BottomNavHost(navController, logout)
        }
    }
}