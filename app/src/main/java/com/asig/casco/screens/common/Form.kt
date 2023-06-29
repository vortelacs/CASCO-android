package com.asig.casco.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownMenu(
    items: List<String>,
    enabled: Boolean = true,
    label: String,
    selectedIndex: MutableState<Int>,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column() {
        Box(modifier = Modifier.height(IntrinsicSize.Min).
        fillMaxWidth()) {
            OutlinedTextField(
                value = if (selectedIndex.value in items.indices) items[selectedIndex.value] else "",
                readOnly = true,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(label) },
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
            items.forEachIndexed { index, label ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex.value = index
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
fun inpuText(
    label : String

){

    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
    ) {

        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier.fillMaxWidth().
            padding(top = 10.dp),
            label = { Text(text = label) },
        )
    }
}

@Composable
fun checkBox(
    label : String,
    checked: MutableState<Boolean>
){

    Row(verticalAlignment = Alignment.CenterVertically) {
    Checkbox(
        checked = checked.value,
        onCheckedChange = { checked_ ->
            checked.value = checked_
        }
    )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = label
        )
    }
}
