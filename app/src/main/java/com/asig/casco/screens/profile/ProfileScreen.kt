package com.asig.casco.screens.profile

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asig.casco.R
import com.asig.casco.api.viewmodel.InsuranceViewModel
import com.asig.casco.api.viewmodel.PDFViewModel
import com.asig.casco.model.Insurance
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = "Bună John Doy",
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


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun ExpandableInsuranceCard(
    insurance : Insurance
) {
    val pdfViewModel : PDFViewModel = hiltViewModel()

    val pdfResult by pdfViewModel.pdfResult.collectAsState()

    var expandedState by remember { mutableStateOf(false) }
    Column {
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
                        maxLines = if (!expandedState) 1 else 3,
                        overflow = if (!expandedState) TextOverflow.Ellipsis else TextOverflow.Visible,
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
                        Text(
                            text = "Date automobil:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 2.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                TextWithLabel("Model", insurance.vehicle.model)
                                Spacer(modifier = Modifier.height(4.dp))
                                TextWithLabel("Marca", insurance.vehicle.make)
                                Spacer(modifier = Modifier.height(4.dp))
                                TextWithLabel("An", insurance.vehicle.year.toString())
                                Spacer(modifier = Modifier.height(4.dp))
                                TextWithLabel("Preț vehicul", insurance.vehicle.carPrice.toString() + " lei")
                                Spacer(modifier = Modifier.height(4.dp))
                                TextWithLabel(
                                    "Nr. certificatului",
                                    insurance.vehicle.certificateNumber
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                TextWithLabel(
                                    "Nr. de înregistrare",
                                    insurance.vehicle.registrationNumber
                                )
                            }
                        }

                        Text(
                            text = "Datele persoanei/persoanelor",
                            fontSize = 16.sp,
                        )

                        insurance.persons.forEach { person ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 2.dp
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    TextWithLabel("Prenume", person.firstName)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    TextWithLabel("Nume", person.lastName)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    TextWithLabel("IDNP", person.idnp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    TextWithLabel("Telefon", person.phone)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LaunchedEffect(key1 = "loadpdf") {
                        pdfViewModel.getPDF(insurance.effectiveDate, insurance.price)
                    }
                    if (pdfResult.isNotEmpty()) {
                        DownloadPdfButton(pdfResult)
                    }
                    Spacer(modifier = Modifier.height(4.dp))

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

@Composable
fun DownloadPdfButton(pdfUrl: String) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End){
    Button(onClick = {
        val request = DownloadManager.Request(Uri.parse(pdfUrl))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Uri.parse(pdfUrl).lastPathSegment)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
        Toast.makeText(context, "Se descarcă...", Toast.LENGTH_SHORT).show()
    }) {
        Text("Descarcă pdf")
    }
    }
}