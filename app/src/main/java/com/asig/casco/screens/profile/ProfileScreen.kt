package com.asig.casco.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.asig.casco.screens.common.BottomBar
import com.asig.casco.screens.home.PagerCarousel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = false)
fun ProfileScreen(
    navigator: DestinationsNavigator
){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navigator)
        }
    ) { innerPadding ->
        Surface(

            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {

            }
        }
//        DestinationsNavHost(
//            navController = navController //!! this is important
//            // ...
//        )
    }

}