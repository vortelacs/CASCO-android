package com.asig.casco.screens.assurance

import androidx.compose.foundation.clickable
import com.asig.casco.screens.destinations.AssuranceFormScreenDestination
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.asig.casco.R
import com.asig.casco.screens.common.ImageCard
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = false)
@Composable
fun AssuranceScreen(
    navigator: DestinationsNavigator
){

ScaffoldSkeleton(navigator = navigator, titleBar = "Asigurare noua") {

    val modifier : Modifier

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        ImageCard(painter = painterResource(R.drawable.casco),
            contentDescription = "Casco",
            title = "Casco",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.navigate(AssuranceFormScreenDestination) }
        )
        ImageCard(painter = painterResource(R.drawable.rca),
            contentDescription = "RCA",
            title = "RCA",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.navigate(AssuranceFormScreenDestination) }
        )
        ImageCard(painter = painterResource(R.drawable.carte_verde),
            contentDescription = "Carte Verde",
            title = "Carte Verde",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigator.navigate(AssuranceFormScreenDestination) }
        )
    }

}
}