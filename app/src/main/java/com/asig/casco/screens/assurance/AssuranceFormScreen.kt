package com.asig.casco.screens.assurance

import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.PopupProperties
import com.asig.casco.ui.theme.CASCOTheme
import com.google.android.material.color.MaterialColors.ALPHA_DISABLED
import com.google.android.material.color.MaterialColors.ALPHA_FULL

@Destination(start = false)
@Composable
fun AssuranceFormScreen(
    navigator: DestinationsNavigator
) {


    ScaffoldSkeleton(navigator = navigator, titleBar = "New assurance") {
        val listItems = arrayListOf("Favorites", "Options", "Settings", "Share")
        var selectedIndex by remember { mutableStateOf(0) }
       dropDownMenu(listItems)

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenu(
    items: List<String>,
    enabled: Boolean = true,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(Modifier.padding(20.dp)) {
        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            OutlinedTextField(
                value = selectedText,
                readOnly = true,
                onValueChange = { selectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Label") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .clickable(enabled = enabled) { expanded = true },
                color = Color.Transparent,
            ) {}
        }

            DropdownMenu(
                expanded = expanded,
                properties = PopupProperties(focusable = true),
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                items.forEach { label ->
                    DropdownMenuItem(
                        onClick = {

                            selectedText = label
                            expanded = false
                        },
                        text = {
                            Text(text = label)
                        })
                }
            
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <String> LargeDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<String>,
    selectedIndex: Int = 0,
    onItemSelected: (index: Int, item: String) -> Unit,
    drawItem: @Composable (String, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = items[selectedIndex].toString(),
            label = { Text(text = label.toString()) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                val icon = if (expanded)  Icons.Filled.ArrowDropDown else Icons.Filled.KeyboardArrowUp
////                val icon = expanded.select(Icons.Filled.ArrowDropUp, Icons.Filled.ArrowDropDown)
//                Icon(icon, "")
//            },
            onValueChange = { },
            readOnly = true,
        )

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(enabled = enabled) { expanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            CASCOTheme() {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                ) {
                    val listState = rememberLazyListState()
                    if (selectedIndex > -1) {
                        LaunchedEffect("ScrollToSelected") {
                            listState.scrollToItem(index = selectedIndex)
                        }
                    }

                    for (item in items) {
                        DropdownMenuItem(
                            text = { item },
                            onClick = {
                                expanded = false
                                onItemSelected(items.indexOf(item), item)
                            })

                    }
                }
            }
        }
    }
}


    @Composable
    fun LargeDropdownMenuItem(
        text: String,
        selected: Boolean,
        enabled: Boolean,
        onClick: () -> Unit,
    ) {
        val contentColor = when {
            !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = ALPHA_DISABLED)
            selected -> MaterialTheme.colorScheme.primary.copy(alpha = ALPHA_FULL)
            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = ALPHA_FULL)
        }

        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Card(modifier = Modifier
                .clickable(enabled) { onClick() }
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }


/*            Dialog(
                onDismissRequest = { expanded = false },
            ) {
                CASCOTheme() {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                    ) {


                        val listState = rememberLazyListState()
                        if (selectedIndex > -1) {
                            LaunchedEffect("ScrollToSelected") {
                                listState.scrollToItem(index = selectedIndex)
                            }
                        }

                        for (item in items) {

                            LargeDropdownMenuItem(
                                text = item,
                                selected = false,
                                enabled = false,
                                onClick = { },
                            )

                                        DropdownMenuItem(
                                text = {  },
                                onClick = {
                                    expanded = false
                                    onItemSelected(items.indexOf(item), item)
                                })


                            */



