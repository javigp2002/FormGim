package com.example.formgim.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.formgim.components.MyBottomNavBar
import com.example.formgim.navigation.Navigation

@Composable
fun MainScreen (){
    val navcontroller = rememberNavController()
    Scaffold (
        bottomBar = {
            MyBottomNavBar(navcontroller)
        }
    ){ padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ){
            Navigation(navController = navcontroller)
        }
    }
}