package com.asig.casco.screens.home

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.navigation.compose.rememberNavController
import com.asig.casco.screens.common.BottomBar


@Composable
@Destination(start = false)
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()

    data class CarouselItem(val title: String)

    val carouselItems = listOf(
        CarouselItem(title = "Item 1"),
        CarouselItem(title = "Item 2"),
        CarouselItem(title = "Item 3"),
        // Add more items as needed
    )



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
                PagerCarousel()
            }
        }
    }
}