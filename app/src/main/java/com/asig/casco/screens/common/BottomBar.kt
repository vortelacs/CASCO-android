package com.asig.casco.screens.common

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.asig.casco.R
import com.asig.casco.screens.destinations.AssuranceScreenDestination
import com.asig.casco.screens.destinations.HomeScreenDestination
import com.asig.casco.screens.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Composable
fun BottomBar(
    navigator: DestinationsNavigator
) {
    BottomNavigation {
        BottomBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = true,
                onClick = {
                    navigator.navigate(destination.direction)
                },
                icon = { Icon(destination.icon, contentDescription = stringResource(destination.label))},
                label = { Text(stringResource(destination.label)) },
            )
        }
    }

}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home_screen),
    Assurance(AssuranceScreenDestination, Icons.Default.List , R.string.assurance_buy_screen),
    Profile(ProfileScreenDestination, Icons.Default.Person, R.string.profile_screen)

}