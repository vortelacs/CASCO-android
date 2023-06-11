package com.asig.casco.screens.home

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.navigation.compose.rememberNavController
import com.asig.casco.screens.common.BottomBar
import com.asig.casco.screens.common.TopBar


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
        topBar = {
            TopBar("CascoMD", navigator)
        },

        bottomBar = {
            BottomBar(navigator)
        }
    ) { innerPadding ->
        Surface(

            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                Text(text = "News", style = MaterialTheme.typography.body1 )
                PagerCarousel()
                Text(text = "Parteneri", style = MaterialTheme.typography.body1 )
            }
        }
    }
}