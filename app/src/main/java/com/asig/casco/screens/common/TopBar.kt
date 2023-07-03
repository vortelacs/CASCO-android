package com.asig.casco.screens.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.asig.casco.R
import com.asig.casco.screens.destinations.AboutAppScreenDestination
import com.asig.casco.screens.destinations.ContactScreenDestination
import com.asig.casco.screens.destinations.FAQScreenDestination
import com.asig.casco.screens.destinations.LoginScreenDestination
import com.asig.casco.screens.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Composable
fun TopBar(
    title: String,
    navigator: DestinationsNavigator
) {
    TopAppBar() {
        val listItems = getMenuItemsList()

        // state of the menu
        var expanded by remember {
            mutableStateOf(false)
        }

        TopAppBar(
            title = {
                Text(text = title)
            },
            actions = {

                // 3 vertical dots icon
                IconButton(onClick = {
                    expanded = true
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Open Options"
                    )
                }

                // drop down menu
                DropdownMenu(
                    modifier = Modifier.width(width = 150.dp),
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    // adjust the position
                    offset = DpOffset(x = (-102).dp, y = (-64).dp),
                    properties = PopupProperties()
                ) {

                    // adding each menu item
                    listItems.forEach { menuItemData ->
                        DropdownMenuItem(
                            onClick = {
                                navigator.navigate(menuItemData.direction)
                            },
                            enabled = true
                        ) {

                            Icon(
                                imageVector = menuItemData.icon,
                                contentDescription = stringResource(menuItemData.text)
                            )

                            Spacer(modifier = Modifier.width(width = 8.dp))

                            Text(
                                text = stringResource(menuItemData.text),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }
        )
    }
}

    @OptIn(ExperimentalMaterialApi::class)
    fun getMenuItemsList(): ArrayList<MenuItemData> {
        val listItems = ArrayList<MenuItemData>()

        listItems.add(MenuItemData(FAQScreenDestination, text = R.string.faq_screen, icon = Icons.Outlined.Face))
        listItems.add(MenuItemData(ContactScreenDestination, text = R.string.contact_screen, icon = Icons.Outlined.Email))
        listItems.add(MenuItemData(AboutAppScreenDestination, text = R.string.about_app_screen, icon = Icons.Outlined.Info))
        listItems.add(MenuItemData(SettingsScreenDestination, text = R.string.settings_screen, icon = Icons.Outlined.Settings))
        listItems.add(MenuItemData(LoginScreenDestination, text = R.string.log_out, icon = Icons.Outlined.ExitToApp))

        return listItems
    }
    data class MenuItemData(val direction: DirectionDestinationSpec, val text: Int, val icon: ImageVector)