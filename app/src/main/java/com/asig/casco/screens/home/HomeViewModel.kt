package com.asig.casco.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.asig.casco.model.Insurer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerCarousel(

) {
    val insurerList = remember { mutableStateListOf<Insurer>() }
    val context = LocalContext.current
//    sendRequestgetAll(insurerList, context)

    HorizontalPager(pageCount = 10) { page ->
        Card(modifier = Modifier.
            fillMaxWidth().

            height(200.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        border = null,
        elevation = 1.dp
        ) {
            Text(text = "123123")
        }
    }
}
