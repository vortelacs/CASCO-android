package com.asig.casco.screens.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asig.casco.api.utils.EmailUtil
import com.asig.casco.screens.common.SimplePopup
import com.asig.casco.screens.destinations.HomeScreenDestination
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import jakarta.mail.MessagingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalMaterialApi
@Destination(start = false)
@Composable
fun ContactScreen (
    navigator: DestinationsNavigator
){
    ScaffoldSkeleton(navigator = navigator, titleBar = "Contact") {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Contact Information
            ContactInfoSection()

            Spacer(modifier = Modifier.height(32.dp))

            // Email Form
            EmailFormSection(navigator)
        }

    }

}

@Composable
fun ContactInfoSection() {
    Column {
        Text(
            text = "Informații de contact",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Email: suport@casco.md")
        Text("Telefon: (123) 456-7890")
        // Add more contact information fields as needed
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailFormSection(
    navigator: DestinationsNavigator
) {
    val coroutineScope = rememberCoroutineScope()
    val emailSentStatus = remember { mutableStateOf(false) }
    Column {
        Text(
            text = "Trimite un email",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val nameState = remember { mutableStateOf("") }
        val emailState = remember { mutableStateOf("") }
        val messageState = remember { mutableStateOf("") }

        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Nume") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = messageState.value,
            onValueChange = { messageState.value = it },
            label = { Text("Mesaj") },
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        sendEmail(nameState.value, emailState.value, messageState.value)
                        emailSentStatus.value = true
                        // Show success message or perform other actions upon successful email sending
                    } catch (e: MessagingException) {
                        // Handle the exception here
                        e.printStackTrace()
                        // Show error message or perform other error handling
                    }
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Trimite")
        }
        if (emailSentStatus.value) {
            SimplePopup(
                message = "Mesajul a fost transmis cu succes",
                onDismiss = { emailSentStatus.value = false
                navigator.navigate(HomeScreenDestination)}
            )
        }
    }
}

suspend fun sendEmail(name: String, email: String, message: String) {
    withContext(Dispatchers.IO) {
        EmailUtil.sendEmail(name, email, message)
    }
}