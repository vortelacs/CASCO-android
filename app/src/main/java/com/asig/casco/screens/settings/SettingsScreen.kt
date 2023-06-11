package com.asig.casco.screens.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asig.casco.screens.common.BottomBar
import com.asig.casco.screens.common.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator



@Composable
@Destination(start = false)
fun SettingsScreen(
    navigator: DestinationsNavigator
) {
//    var darkTheme by remember { mutableStateOf(false)}


    Scaffold(
        topBar = {
            TopBar("Setări", navigator)
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
/*                ThemeSwitch(
                    darkTheme = darkTheme,
                    onThemeChanged = { isDarkTheme -> darkTheme = isDarkTheme }
                )*/

            }
        }
        }
    }



/*
@Composable
fun ThemeSwitch(
    darkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        androidx.compose.material3.Text(text = "Dark Mode", style = MaterialTheme.typography.h6)
        Switch(
            checked = darkTheme,
            onCheckedChange = onThemeChanged,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}*/
