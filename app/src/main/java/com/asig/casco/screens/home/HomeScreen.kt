package com.asig.casco.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.navigation.compose.rememberNavController
import com.asig.casco.screens.skeleton.ScaffoldSkeleton


@Composable
@Destination(start = false)
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()




    ScaffoldSkeleton(navigator = navigator, titleBar = "Profil") {

                Text(text = "News", style = MaterialTheme.typography.body1 )
                PagerCarousel()
                Text(text = "Parteneri", style = MaterialTheme.typography.body1 )
    }

}