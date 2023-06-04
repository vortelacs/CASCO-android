package com.asig.casco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.asig.casco.screens.NavGraphs
import com.asig.casco.ui.theme.CASCOTheme
import com.ramcosta.composedestinations.DestinationsNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CASCOTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}