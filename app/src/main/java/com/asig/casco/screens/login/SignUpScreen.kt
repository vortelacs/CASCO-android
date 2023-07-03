package com.asig.casco.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asig.casco.model.SignUpRequest
import com.asig.casco.screens.destinations.HomeScreenDestination
import com.asig.casco.screens.destinations.LoginScreenDestination
import com.asig.casco.security.AuthViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = false)
@Composable
fun SignUp(navigator: DestinationsNavigator) {

    val context = LocalContext.current
    val authViewModel = AuthViewModel(context)
    val signUpAttempted by authViewModel.signUpAttempted.collectAsState()

    LaunchedEffect(key1 = authViewModel) {
        authViewModel.signUpResult.collect { signUpResult ->
            if (signUpResult) {
                navigator.navigate(HomeScreenDestination)
            } else if (signUpAttempted) {
                Toast.makeText(context, "Probleme cu conexiunea", Toast.LENGTH_LONG).show()
            }
        }
    }




    val firstName = remember {
        mutableStateOf(TextFieldValue())
    }
    val lastName = remember {
        mutableStateOf(TextFieldValue())
    }
    val email = remember {
        mutableStateOf(TextFieldValue())
    }
    val idnp = remember {
        mutableStateOf(TextFieldValue())
    }
    val phone = remember {
        mutableStateOf(TextFieldValue())
    }
    val password = remember {
        mutableStateOf(TextFieldValue())
    }
    val passwordConfirm = remember {
        mutableStateOf(TextFieldValue())
    }
    val screenNumber = remember {
        mutableStateOf(1)
    }


            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Înregistrare",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Default)
                )

                Spacer(modifier = Modifier.height(15.dp))

                if(screenNumber.value == 1) {
                CreatePerson(phone, firstName, lastName, idnp)
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            screenNumber.value = 2
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Mai departe")
                    }
                }
            }else {
                CreateUser(email, password, passwordConfirm)
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            screenNumber.value = 1
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Înapoi")
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            val request = SignUpRequest(
                                email = email.value.text,
                                password = password.value.text,
                                firstName = firstName.value.text,
                                lastName = lastName.value.text,
                                idnp = idnp.value.text,
                                phone = phone.value.text,
                            )

                            authViewModel.signUp(request)
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Creează cont")
                    }
                }
            }
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            navigator.navigate(LoginScreenDestination)
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Ai deja cont? Loghează-te aici")
                    }
                }

            }
        }

@Composable
fun CreatePerson(
    phone : MutableState<TextFieldValue>,
    firstName: MutableState<TextFieldValue>,
    lastName: MutableState<TextFieldValue>,
    idnp: MutableState<TextFieldValue>,
    ){


    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "Număr de telefon") },
        value = phone.value,
        onValueChange = { phone.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Prenume") },
        value = firstName.value,
        onValueChange = { firstName.value = it }
    )

    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "Nume") },
        value = lastName.value,
        onValueChange = { lastName.value = it }
    )

    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "IDNP") },
        value = idnp.value,
        onValueChange = { idnp.value = it }
    )

}

@Composable
fun CreateUser(
    email : MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    passwordConfirm: MutableState<TextFieldValue>,
){


    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "Email") },
        value = email.value,
        onValueChange = { email.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )


    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "Parola") },
        value = password.value,
        onValueChange = { password.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

    Spacer(modifier = Modifier.height(15.dp))

    TextField(
        label = { Text(text = "Confirmați parola") },
        value = passwordConfirm.value,
        onValueChange = { passwordConfirm.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}