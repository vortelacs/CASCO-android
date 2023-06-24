package com.asig.casco.screens.FAQ

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.PopupProperties
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asig.casco.R

@ExperimentalMaterialApi
@Destination(start = false)
@Composable
fun FAQScreen (
    navigator: DestinationsNavigator
){
    ScaffoldSkeleton(navigator = navigator, titleBar = "Întrebări frecvente") {
        Column {
            MainFAQToolbar()
            FAQSection()
        }

        BottomText()

    }

}


@Composable
fun BottomText() {
    Box (
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.padding(20.dp)
    ){
        Text(text = "Can't find an answer to your questions? Feel free to contact us at help@abcd.com",
            fontSize = 12.sp,
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun FAQSection() {
    Column() {
        ExpandableFAQCard(
                "What is Malware ?", "Malware is intrusive software that is " +
        "designed to damage and destroy computers and computer systems. " +
                "Malware is a contraction for “malicious software" +
                "."
        )
        ExpandableFAQCard(
            "How do I contact customer service ?", "You may write us at abcd@ab.com" +
                    " with your query/concern and we'll get back to you as soon as possible."
        )
        ExpandableFAQCard(
            "What will be the duration of the service ?",
            "The members can be admitted under the policy at well defined date for full " +
                    "Cover term (1 Year) from their scheme joining date."
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ExpandableFAQCard(title: String, description: String) {
    var expandedState by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 400,
                    easing = LinearOutSlowInEasing
                )
            ),
        onClick = {
            expandedState = !expandedState
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    modifier = Modifier.weight(6f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines =if(!expandedState) 1 else 3,
                    overflow =if(!expandedState) TextOverflow.Ellipsis else TextOverflow.Visible,
                )
                IconButton(
                    onClick = { expandedState = !expandedState },
                    modifier = Modifier
                        .weight(1f)
                        .alpha(.5f),
                ) {
                    if (expandedState)
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = "",
                            modifier = Modifier.size(20.dp),
                        )
                    else
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = "",
                            modifier = Modifier.size(20.dp),
                        )


                }
            }

            if (expandedState) {
                Text(
                    text = description,
                    modifier = Modifier.padding(bottom = 40.dp),
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )
            }

        }

    }
}

@Composable
fun MainFAQToolbar() {


    Text(
        text = "Frequently Asked Questions",
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding( vertical = 30.dp)
    )


}


/*
@Composable
fun dropdownInfo(
    enabled: Boolean = true,
    label: String
){
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {
        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            OutlinedTextField(
                value = "selectedText",
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text("label") },
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

            }

        }
    }
*/

