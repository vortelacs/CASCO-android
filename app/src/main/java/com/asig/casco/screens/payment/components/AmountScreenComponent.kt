package com.asig.casco.screens.payment.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asig.casco.screens.payment.ui.GreenLight
import com.asig.casco.screens.payment.ui.YellowLight


@Composable
@Preview
fun HeaderCircles() {

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp), onDraw = {
        val canvasHeight = size.height
        drawCircle(
            color = YellowLight,
            center = Offset(
                x = 170f,
                y = -50f
            ),
            radius = canvasHeight,
        )
        drawCircle(
            color = GreenLight,
            center = Offset(
                x = 0f,
                y = 0f
            ),
            radius = canvasHeight
        )
    })
}


@Composable
fun WalletAmount() {
    Column(Modifier.padding(vertical = 16.dp)) {
        Text(
            text = "Compose Wallet",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        Text(
            text = "$20",
            color = GreenLight,
            fontWeight = FontWeight.Bold,
            fontSize = 33.sp
        )
    }
}

@Composable
@Preview
fun AmountEnterCard() {

    var amount by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp, Color.LightGray
        ),
        backgroundColor = Color.White, modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),

            ) {
            Text(text = "Paying to John Doe", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))


            AmountTextField(value = amount, onValueChange = {
                amount = it
            }, placeholder = "Enter Amount")

            Spacer(modifier = Modifier.height(30.dp))

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = message, onValueChange = {
                    message = it
                }, colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedLabelColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ), modifier = Modifier
                    .align(CenterHorizontally),
                placeholder = {
                    Text(text = "Add Message")
                }
            )

        }
    }
}


@Composable
fun AmountTextField(value: String, onValueChange: (String) -> Unit, placeholder: String) {

    TextField(
        value = value, onValueChange = onValueChange, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedLabelColor = Color.Gray,

            ),
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "Money")
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 20.sp)
    )
}


@Composable
fun CustomizedButton(modifier: Modifier=Modifier,
                     text:String,
                     onClick: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Button(
            onClick = onClick, colors = ButtonDefaults.buttonColors(
                backgroundColor = GreenLight,
            ), shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(text = text)
        }
    }

}