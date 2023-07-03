package com.asig.casco.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@com.ramcosta.composedestinations.annotation.Destination(start = false)
@Composable
fun AboutAppScreen (
    navigator: DestinationsNavigator
){
    ScaffoldSkeleton(navigator = navigator, titleBar = "Despre casco.md") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Despre noi",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "CascoMD este o companie din Republica Moldova care oferă servicii în domeniul asigurărilor.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Aplicația de Asigurări CASCO", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Versiunea 1.0.0")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Aplicația de Asigurări CASCO este concepută pentru a simplifica procesul de achiziție și gestionare a asigurărilor auto. Cu ajutorul aplicației noastre, puteți:")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "1. Achiziționați rapid și ușor asigurări pentru vehiculul dvs.")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "2. Gestionați politicile de asigurare, vizualizați detaliile poliței și faceți modificări după cum este necesar.")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "3. Depuneți cereri și urmăriți progresul acestora.")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "4. Luați legătura cu echipa noastră de servicii pentru clienți pentru orice asistență.")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ne angajăm să oferim o experiență de asigurare fără probleme utilizatorilor noștri. Dacă aveți feedback sau sugestii, nu ezitați să ne contactați.")
        }
    }
}

