package com.asig.casco.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SignUp(navController: NavController) {


            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val username = remember {
                    mutableStateOf(TextFieldValue())
                }

                val password = remember {
                    mutableStateOf(TextFieldValue())
                }

                val passwordConfirm = remember {
                    mutableStateOf(TextFieldValue())
                }

                Text(
                    text = "SignUp",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    onValueChange = { password.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "Password Confirmation") },
                    value = passwordConfirm.value,
                    onValueChange = { passwordConfirm.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "SignUp")
                    }
                }
            }
        }

