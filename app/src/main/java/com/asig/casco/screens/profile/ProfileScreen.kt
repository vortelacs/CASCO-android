package com.asig.casco.screens.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.asig.casco.R
import com.asig.casco.model.Insurance
import com.asig.casco.screens.common.BottomBar
import com.asig.casco.screens.home.PagerCarousel
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator




@OptIn(ExperimentalMaterialApi::class)
@Composable
@Destination(start = false)
fun ProfileScreen(
    navigator: DestinationsNavigator
) {


    var insurances = ArrayList<Insurance>()
    insurances.add(Insurance("1", "2", "3", ""))
    insurances.add(Insurance("1", "2", "3", ""))
    insurances.add(Insurance("1", "2", "3", ""))
    insurances.add(Insurance("1", "2", "3", ""))

    ScaffoldSkeleton(navigator = navigator, titleBar = "Profil") {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(R.drawable.profile_blank),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
            Text(
                text = "user.fullName",
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(45.dp))
            androidx.compose.material.Text(
                text = "Asigurari",
                style = MaterialTheme.typography.body1
            )
            insurances.forEach { insurance ->
                ExpandableInsuranceCard(insurance)
            }
        }
    }
}

@Composable
fun InsuranceItem(insurance: Insurance) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Insurance Type: ${insurance.type}")
            Text(text = "Price: ${insurance.id}")
            // Add more fields as needed
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ExpandableInsuranceCard(
    /*content: @Composable() () -> Unit*/
    insurance : Insurance
) {
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
                    text = insurance.type,
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
                    text = insurance.person,
                    modifier = Modifier.padding(bottom = 40.dp),
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )
            }

        }

    }
}