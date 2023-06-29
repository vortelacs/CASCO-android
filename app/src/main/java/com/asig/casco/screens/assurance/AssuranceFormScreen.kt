package com.asig.casco.screens.assurance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.TariffApi
import com.asig.casco.api.viewmodel.InsuranceViewModel
import com.asig.casco.api.viewmodel.TariffViewModel
import com.asig.casco.model.Tariff
import com.asig.casco.screens.common.checkBox
import com.asig.casco.screens.common.dropDownMenu
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = false)
@Composable
fun AssuranceFormScreen(
    navigator: DestinationsNavigator
) {

    val tariffViewModel : TariffViewModel = hiltViewModel()
    val insuranceViewModel : InsuranceViewModel = hiltViewModel()
    var tariff : Tariff

    val tariffPrice by tariffViewModel.getTariffResult.collectAsState(initial = 0)
/*
    LaunchedEffect(key1 = tariffViewModel) {
        tariffViewModel.getTariffResult.collect { tariffResult ->
            if(tariffPrice != -1)
        }
    }*/

    LaunchedEffect(key1 = "loadInsurances") {
        insuranceViewModel.getInsuranceByEmail("johndoy1@mail.ru")
    }


    val retrofitTariff = RetrofitFactory().getRetrofitInstance()
    retrofitTariff.create(TariffApi::class.java)

    val firstName = remember {
        mutableStateOf(TextFieldValue())
    }
    val lastName = remember {
        mutableStateOf(TextFieldValue())
    }
    val IDNP = remember {
        mutableStateOf(TextFieldValue())
    }
/*    val email = remember {
        mutableStateOf(TextFieldValue())
    }*/
    val phone = remember {
        mutableStateOf(TextFieldValue())
    }
    val additionalIDNP = remember {
        mutableStateOf(TextFieldValue())
    }
    val additionalFirstName = remember {
        mutableStateOf(TextFieldValue())
    }
    val additionalLastName = remember {
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

    val showConfirmDialog = remember { mutableStateOf(false) }

    val selectedType = remember {
        mutableStateOf(0)
    }
    val showAdditionalPerson = remember { mutableStateOf(false) }
    val acceptDataProcessing = remember { mutableStateOf(false) }
    val acceptTerms = remember { mutableStateOf(false) }

    ScaffoldSkeleton(navigator = navigator, titleBar = "New assurance") {


        Column(modifier = Modifier.padding(45.dp, 10.dp, 45.dp, 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(modifier = Modifier.height(15.dp))

            if(screenNumber.value == 1) {
                personDetails(firstName, lastName, phone, IDNP, additionalFirstName, additionalLastName,additionalIDNP, showAdditionalPerson)
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
                vehicleDetails(selectedType, make, model, year, price, selectedCurrency, certificateNumber, registrationNumber)
                Spacer(modifier = Modifier.height(15.dp))
                checkBox("Accept termenii si conditiile", acceptDataProcessing)
                checkBox("Accept prelucrarea datelor cu caracter personal", acceptTerms)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            screenNumber.value = 1
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            //.padding(40.dp, 0.dp, 20.dp, 0.dp)
                    ) {
                        Text(text = "Înapoi")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            if(year.value.text.isNotBlank() && price.value.text.isNotBlank()) {

                                tariff = Tariff(
                                    insurer = "moldasig",
                                    insuranceType = "casco",
                                    vehicleType =  getCarTypesList()[selectedType.value],
                                    age = year.value.text.toInt(),
                                    isFranchise = false
                                )
                                tariffViewModel.getTarriff(tariff)
                            }
                        },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                            //.padding(20.dp, 0.dp, 40.dp, 0.dp),
                        enabled = year.value.text.isNotBlank() && price.value.text.isNotBlank()
                    ) {
                        Text(text = "Verifică prețul")
                    }
                }
                if(tariffPrice != 0 && price.value.text.isNotBlank()) {
//                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(modifier = Modifier.
                        padding(0.dp, 20.dp),
                        color = Color.Gray, thickness = 1.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ("%.2f".format(tariffPrice.toDouble() * price.value.text.toDouble() / 100)).toString() + " lei",
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                if (firstName.value.text.isNotBlank() &&
                                    lastName.value.text.isNotBlank() &&
                                    IDNP.value.text.isNotBlank() &&
                                    phone.value.text.isNotBlank() &&
                                    (!showAdditionalPerson.value ||
                                            (additionalFirstName.value.text.isNotBlank() &&
                                                    additionalLastName.value.text.isNotBlank() &&
                                                    additionalIDNP.value.text.isNotBlank())) &&
                                    make.value.text.isNotBlank() &&
                                    model.value.text.isNotBlank() &&
                                    year.value.text.isNotBlank() &&
                                    price.value.text.isNotBlank() &&
                                    certificateNumber.value.text.isNotBlank() &&
                                    registrationNumber.value.text.isNotBlank()) {

                                    // Add your API request code here

                                    // Show confirmation dialog
                                    showConfirmDialog.value = true
                                }
                            },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                        ) {
                            Text(text = "Procură")
                        }
                    }

                    if (showConfirmDialog.value) {
                        AlertDialog(
                            onDismissRequest = {
                                showConfirmDialog.value = false
                            },
                            title = { Text(text = "Confirmation") },
                            text = { Text("Your request has been sent.") },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showConfirmDialog.value = false
                                    }
                                ) {
                                    Text("OK")
                                }
                            }
                        )
                    }
                }

            }
            

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun personDetails(
    firstName : MutableState<TextFieldValue>,
    lastName : MutableState<TextFieldValue>,
    phone : MutableState<TextFieldValue>,
    IDNP : MutableState<TextFieldValue>,
    aditionalFirstName: MutableState<TextFieldValue>,
    aditionalLastName: MutableState<TextFieldValue>,
    aditionalIDNP: MutableState<TextFieldValue>,
    showAdditionalPerson: MutableState<Boolean>
){

    CustomOutlinedTextField(
        value = firstName,
        label = "Prenume",
        onValueChange = { firstName.value = it },
        isError = firstName.value.text.isBlank(),
        errorMessage = "This field cannot be empty"
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = lastName,
        label = "Nume",
        onValueChange = { lastName.value = it },
        isError = lastName.value.text.isBlank(),
        errorMessage = "This field cannot be empty"
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = IDNP,
        label = "IDNO/IDNP",
        onValueChange = { IDNP.value = it },
        isError = IDNP.value.text.isBlank(),
        errorMessage = "This field cannot be empty"
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = phone,
        label = "Număr de telefon",
        onValueChange = { phone.value = it },
        isError = phone.value.text.isBlank(),
        errorMessage = "This field cannot be empty"
    )

    Spacer(modifier = Modifier.height(15.dp))

    checkBox(
        label = "Adăugați o persoană suplimentară",
        checked = showAdditionalPerson
    )
    if(showAdditionalPerson.value){
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            label = { Text(text = "Prenume(șofer adițional)") },
            value = aditionalFirstName.value,
            onValueChange = { aditionalFirstName.value = it },
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            label = { Text(text = "Nume(șofer adițional)") },
            value = aditionalLastName.value,
            onValueChange = { aditionalLastName.value = it },
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            label = { Text(text = "IDNO/IDNP(șofer adițional)") },
            value = aditionalIDNP.value,
            onValueChange = { aditionalIDNP.value = it },

        )
}

    Spacer(modifier = Modifier.height(15.dp))

}

@OptIn(ExperimentalMaterial3Api::class)
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
        label = { Text(text = "Marca") },
        value = make.value,
        onValueChange = { make.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Model") },
        value = model.value,
        onValueChange = { model.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Anul fabricației") },
        value = year.value,
        onValueChange = {
            if (it.text.isFloat()) { // Check if the input is a valid float
                year.value = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
//    dropDownMenu(getCurrencyList(), label = "Model", selectedIndex = currency)
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Prețul pe piață") },
        value = price.value,
        onValueChange = {
            if (it.text.isFloat()|| it.text.isEmpty()) { // Check if the input is a valid float
                price.value = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Numărul certificatului de înmatriculare") },
        value = certificateNumber.value,
        onValueChange = { certificateNumber.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Număr de înregistrare") },
        value = registrationNumber.value,
        onValueChange = { registrationNumber.value = it },

        )
}

fun String.isFloat(): Boolean {
    return toFloatOrNull() != null
}

fun getCarTypesList(): ArrayList<String>{
    return arrayListOf("Autoturism", "Autobuz", "Autocamion, camion pentru semiremorca", "Vehicul agricol", "Troleibuz", "Remorcă / semiremorcă", "Utilaj suplimentar pentru vehicule")
}
fun getCurrencyList(): ArrayList<String>{
    return arrayListOf("Lei", "Euro")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: MutableState<TextFieldValue>,
    label: String,
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean,
    errorMessage: String,
    hasInteracted: MutableState<Boolean>
) {
    OutlinedTextField(
        value = value.value,
        onValueChange = {
            onValueChange(it)
            hasInteracted.value = true
        },
        label = { Text(label) },
        isError = isError && hasInteracted.value,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            errorLabelColor = Color.Red,
            errorTrailingIconColor = Color.Red,
            errorCursorColor = Color.Red,
            errorLeadingIconColor = Color.Red
        )
    )
    if (isError && hasInteracted.value) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}