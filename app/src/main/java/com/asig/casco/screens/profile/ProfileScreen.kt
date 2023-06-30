package com.asig.casco.screens.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asig.casco.R
import com.asig.casco.api.viewmodel.InsuranceViewModel
import com.asig.casco.model.Insurance
import com.asig.casco.model.Person
import com.asig.casco.model.Vehicle
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator



@OptIn(ExperimentalMaterialApi::class)
@Destination(start = false)
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator
) {

    val insuranceViewModel : InsuranceViewModel = hiltViewModel()

/*
    val insurances = ArrayList<Insurance> ( )

    val persons = listOf(
        Person("John", "Doe", "123456", "1234567890", "john.doe@example.com"),
        Person("Jane", "Doe", "234567", "0987654321", "jane.doe@example.com")
    )

    insurances.add(Insurance(Vehicle("asd",    "asdas",   "asdas", 0, 1.0f, "asdasd", "asdasd"),  persons,   "asdasf",    "af",   "sf",   "sfa",   "asdx",   "afs",))
    insurances.add(Insurance(Vehicle("asd",    "asdas",   "asdas", 0, 1.0f, "asdasd", "asdasd"),  persons,   "asdasf",    "af",   "sf",   "sfa",   "asdx",   "afs",))
    insurances.add(Insurance(Vehicle("asd",    "asdas",   "asdas", 0, 1.0f, "asdasd", "asdasd"),  persons,   "asdasf",    "af",   "sf",   "sfa",   "asdx",   "afs",))
*/

    val insurances by insuranceViewModel.getInsurancesResult.collectAsState(initial = ArrayList())
    LaunchedEffect(key1 = "loadInsurances") {
        insuranceViewModel.getInsuranceByEmail("johndoy1@mail.ru")
    }

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
            Text(text = "Insurance Type: ${insurance.insuranceType}")
            Text(text = "Price: ${insurance.insurer}")
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
            .padding(top = 19.dp)
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
                .padding(horizontal = 15.dp, vertical = 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = insurance.insuranceType,
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
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            TextWithLabel("Type", insurance.vehicle.type)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Model", insurance.vehicle.model)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Make", insurance.vehicle.make)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Year", insurance.vehicle.year.toString())
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Car Price", insurance.vehicle.carPrice.toString())
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Certificate Number", insurance.vehicle.certificateNumber)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Registration Number", insurance.vehicle.registrationNumber)
                        }
                    }



                insurance.persons.forEach { person ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            TextWithLabel("First Name", person.firstName)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Last Name", person.lastName)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("IDNP", person.idnp)
                            Spacer(modifier = Modifier.height(4.dp))
                            TextWithLabel("Phone", person.phone)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                }

            }
            }

        }

}


@Composable
fun TextWithLabel(fieldName: String, fieldValue: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = fieldName,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = fieldValue,
            fontSize = 16.sp
        )
    }
}