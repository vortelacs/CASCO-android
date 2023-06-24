package com.asig.casco.screens.assurance

import android.util.Patterns
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.ui.unit.dp
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.TariffApi
import com.asig.casco.screens.common.checkBox
import com.asig.casco.screens.common.dropDownMenu
import com.asig.casco.screens.common.inpuText
import com.asig.casco.screens.destinations.LoginScreenDestination

@Destination(start = false)
@Composable
fun AssuranceFormScreen(
    navigator: DestinationsNavigator
) {
    val retrofitTariff = RetrofitFactory().getRetrofitInstance()
    retrofitTariff.create(TariffApi::class.java)

    val fullName = remember {
        mutableStateOf(TextFieldValue())
    }
    val IDNP = remember {
        mutableStateOf(TextFieldValue())
    }
    val email = remember {
        mutableStateOf(TextFieldValue())
    }
    val phone = remember {
        mutableStateOf(TextFieldValue())
    }
    val additionalIDNP = remember {
        mutableStateOf(TextFieldValue())
    }
    val additionalFullName = remember {
        mutableStateOf(TextFieldValue())
    }
    val model = remember {
        mutableStateOf(TextFieldValue())
    }
    val make = remember {
        mutableStateOf(TextFieldValue())
    }
    val year = remember {
        mutableStateOf(TextFieldValue())
    }
    val price = remember {
        mutableStateOf(TextFieldValue())
    }
    val certificateNumber = remember {
        mutableStateOf(TextFieldValue())
    }
    val registrationNumber = remember {
        mutableStateOf(TextFieldValue())
    }
    val screenNumber = remember {
        mutableStateOf(1)
    }

    val selectedCurrency = remember {
        mutableStateOf(0)
    }

    val selectedType = remember {
        mutableStateOf(0)
    }

    ScaffoldSkeleton(navigator = navigator, titleBar = "New assurance") {


        Column(modifier = Modifier.padding(45.dp, 10.dp, 45.dp, 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(modifier = Modifier.height(15.dp))

            if(screenNumber.value == 1) {
                personDetails(fullName, email, phone, IDNP, additionalFullName, additionalIDNP)
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
                        androidx.compose.material.Text(text = "Mai departe")
                    }
                }
            }else {
                vehicleDetails(selectedType, make, model, year, price, selectedCurrency, certificateNumber, registrationNumber)
                Spacer(modifier = Modifier.height(15.dp))
                checkBox("Accept termenii si conditiile")
                checkBox("Accept prelucrarea datelor cu caracter personal")
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
                        androidx.compose.material.Text(text = "Înapoi")
                    }
                }
            }
            

        }
    }
}

@Composable
fun personDetails(
    fullName : MutableState<TextFieldValue>,
    email : MutableState<TextFieldValue>,
    phone : MutableState<TextFieldValue>,
    IDNP : MutableState<TextFieldValue>,
    aditionalFullName: MutableState<TextFieldValue>,
    aditionalIDNP: MutableState<TextFieldValue>,
){

    var emailError by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Nume si prenume") },
        value = fullName.value,
        onValueChange = { fullName.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "IDNO/IDNP") },
        value = IDNP.value,
        onValueChange = { IDNP.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        value = email.value,
        onValueChange = {
            email.value = it
            emailError = !Patterns.EMAIL_ADDRESS.matcher(it.text).matches()
        },
        label = { Text("Email") },
        isError = emailError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Număr de telefon") },
        value = phone.value,
        onValueChange = { phone.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Nume și prenume(șofer adițional)") },
        value = aditionalFullName.value,
        onValueChange = { aditionalFullName.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "IDNO/IDNP(șofer adițional)") },
        value = aditionalIDNP.value,
        onValueChange = { aditionalIDNP.value = it },

    )

    Spacer(modifier = Modifier.height(15.dp))

}

@Composable
fun vehicleDetails(
    type : MutableState<Int>,
    make : MutableState<TextFieldValue>,
    model : MutableState<TextFieldValue>,
    year: MutableState<TextFieldValue>,
    price: MutableState<TextFieldValue>,
    currency: MutableState<Int>,
    certificateNumber : MutableState<TextFieldValue>,
    registrationNumber: MutableState<TextFieldValue>,
){

    Spacer(modifier = Modifier.height(15.dp))

    dropDownMenu(getCarTypesList(), label = "Tipul vehicului", selectedIndex = type)
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { androidx.compose.material.Text(text = "Marca") },
        value = make.value,
        onValueChange = { make.value = it },
    )
    dropDownMenu(getCurrencyList(), label = "Model", selectedIndex = currency)
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Anul fabricației") },
        value = year.value,
        onValueChange = { year.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Nume și prenume(șofer adițional)") },
        value = certificateNumber.value,
        onValueChange = { certificateNumber.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { androidx.compose.material.Text(text = "Num[r de ]nregistrare") },
        value = registrationNumber.value,
        onValueChange = { registrationNumber.value = it },

        )

    Spacer(modifier = Modifier.height(15.dp))

    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            androidx.compose.material.Text(text = "Mai departe")
        }
    }
}

fun getCarTypesList(): ArrayList<String>{
    return arrayListOf("Autoturism", "Autobuz", "Autocamion", "Vehicul agricol", "Troleibuz", "Remorcă / semiremorcă", "Utilaj suplimentar pentru vehicule")
}
fun getCurrencyList(): ArrayList<String>{
    return arrayListOf("Lei", "Euro")
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <String> LargeDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<String>,
    selectedIndex: Int = 0,
    onItemSelected: (index: Int, item: String) -> Unit,
    drawItem: @Composable (String, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = items[selectedIndex].toString(),
            label = { Text(text = label.toString()) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                val icon = if (expanded)  Icons.Filled.ArrowDropDown else Icons.Filled.KeyboardArrowUp
////                val icon = expanded.select(Icons.Filled.ArrowDropUp, Icons.Filled.ArrowDropDown)
//                Icon(icon, "")
//            },
            onValueChange = { },
            readOnly = true,
        )

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(enabled = enabled) { expanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            CASCOTheme() {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                ) {
                    val listState = rememberLazyListState()
                    if (selectedIndex > -1) {
                        LaunchedEffect("ScrollToSelected") {
                            listState.scrollToItem(index = selectedIndex)
                        }
                    }

                    for (item in items) {
                        DropdownMenuItem(
                            text = { item },
                            onClick = {
                                expanded = false
                                onItemSelected(items.indexOf(item), item)
                            })

                    }
                }
            }
        }
    }
}*/

/*

    @Composable
    fun LargeDropdownMenuItem(
        text: String,
        selected: Boolean,
        enabled: Boolean,
        onClick: () -> Unit,
    ) {
        val contentColor = when {
            !enabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = ALPHA_DISABLED)
            selected -> MaterialTheme.colorScheme.primary.copy(alpha = ALPHA_FULL)
            else -> MaterialTheme.colorScheme.onSurface.copy(alpha = ALPHA_FULL)
        }

        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Card(modifier = Modifier
                .clickable(enabled) { onClick() }
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
*/


/*            Dialog(
                onDismissRequest = { expanded = false },
            ) {
                CASCOTheme() {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                    ) {


                        val listState = rememberLazyListState()
                        if (selectedIndex > -1) {
                            LaunchedEffect("ScrollToSelected") {
                                listState.scrollToItem(index = selectedIndex)
                            }
                        }

                        for (item in items) {

                            LargeDropdownMenuItem(
                                text = item,
                                selected = false,
                                enabled = false,
                                onClick = { },
                            )

                                        DropdownMenuItem(
                                text = {  },
                                onClick = {
                                    expanded = false
                                    onItemSelected(items.indexOf(item), item)
                                })


                            */



