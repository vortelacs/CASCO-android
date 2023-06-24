package com.asig.casco.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.*
import com.asig.casco.model.Token
import com.asig.casco.screens.destinations.HomeScreenDestination
import com.asig.casco.screens.destinations.SignUpDestination
import com.asig.casco.security.TokenStorage
import com.asig.casco.security.AuthViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun LoginScreen(
    navigator: DestinationsNavigator
){
    val context = LocalContext.current
    val tokenStorage =  TokenStorage(context)
    val authViewModel = AuthViewModel(context)

/*    if(authViewModel.checkTokenValid()){
            navigator.navigate(HomeScreenDestination)
    }*/

    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Respectăm datele tale personale. Citește politica noastră de confidențialitate."),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
            )
        )
    }
    Column(

        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Intră în contul tău", style = TextStyle(fontSize = 30.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Parola") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.Start) {

        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
//                    if(authViewModel.login(username.value.text.trim(), password.value.text.trim()))
                        navigator.navigate(HomeScreenDestination)
//                    else
//                        Toast.makeText(context, "Problems with connection", Toast.LENGTH_LONG).show()
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Loghează-te",
                style = TextStyle(
                    fontSize = 17.sp
                ))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
//                    if(authViewModel.login(username.value.text.trim(), password.value.text.trim()))
                    navigator.navigate(SignUpDestination)
//                    else
//                        Toast.makeText(context, "Problems with connection", Toast.LENGTH_LONG).show()
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Nu ai cont? Crează unul aici",
                    style = TextStyle(
                        fontSize = 17.sp
                    ))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Ai uitat parola :"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline
            )
        )

    }


}
