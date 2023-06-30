package com.asig.casco.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.asig.casco.screens.payment.components.CustomizedButton
import com.asig.casco.screens.payment.components.HeaderCircles
import com.asig.casco.screens.payment.components.SuccessCard
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = false)
@Composable
fun SuccessScreen(
    navigator: DestinationsNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(26.dp)
        ) {

            HeaderCircles()
            Spacer(modifier = Modifier.height(16.dp))
            SuccessCard()

        }
        CustomizedButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Back to Home",
            onClick = {})

    }
}