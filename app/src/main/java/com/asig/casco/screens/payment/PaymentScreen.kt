package com.asig.casco.screens.payment

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asig.casco.screens.payment.components.AmountEnterCard
import com.asig.casco.screens.payment.components.CustomizedButton
import com.asig.casco.screens.payment.components.HeaderCircles
import com.asig.casco.screens.payment.components.WalletAmount


@Destination(start = true)
@Composable
fun PaymentScreen(navigator: DestinationsNavigator) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        TopArea {
            HeaderCircles()
            WalletAmount()
            AmountEnterCard()
        }
        CustomizedButton(
            text = "Pay",
            onClick = {  }
        )
    }

}

@Composable
fun TopArea(content: @Composable () -> Unit) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        content()
    }
}