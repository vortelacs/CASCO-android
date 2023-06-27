package com.asig.casco.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter


@Composable
fun ImageCard(
    painter: Painter? = null,
    imageUrl: String = "",
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
//    content: @Composable() () -> Unit
){
    Card(modifier = modifier.
    fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        border = null,

        elevation = 5.dp,
    ) {
        Box(modifier = Modifier.height(200.dp)){
            if(painter !=null)
                Image(painter = painter, contentDescription, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            else if(imageUrl != ""/* && imageUrl.isNotEmpty() && imageUrl.isNotBlank()*/){
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
/*            AsyncImage(
                model = "https://example.com/image.jpg",
                contentDescription = null,
            )*/
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )
            ){}
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
            ){
                Text(title, color = Color.White, fontSize = 16.sp)
            }


        }
    }

}