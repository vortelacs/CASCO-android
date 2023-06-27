package com.asig.casco.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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


    ScaffoldSkeleton(navigator = navigator, titleBar = "Acasă") {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Noutăți", style = MaterialTheme.typography.body1 )
        Spacer(modifier = Modifier.height(10.dp))
        NewsPagerCarousel()
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Parteneri", style = MaterialTheme.typography.body1 )
        Spacer(modifier = Modifier.height(10.dp))
        PartnerPagerCarousel()
        Spacer(modifier = Modifier.height(10.dp))
    }

}