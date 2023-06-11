package com.asig.casco.screens.skeleton

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
import com.asig.casco.screens.common.BottomBar
import com.asig.casco.screens.common.TopBar
import com.asig.casco.screens.home.PagerCarousel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ScaffoldSkeleton(
    navigator: DestinationsNavigator,
    titleBar : String,
    content: @Composable() () -> Unit
) {

    Scaffold(
        topBar = {
            TopBar(titleBar, navigator)
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
                content()
            }
        }
    }
}