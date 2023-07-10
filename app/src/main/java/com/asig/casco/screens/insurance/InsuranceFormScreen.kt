package com.asig.casco.screens.insurance

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asig.casco.api.build.RetrofitFactory
import com.asig.casco.api.interfaces.TariffApi
import com.asig.casco.api.viewmodel.InsuranceViewModel
import com.asig.casco.api.viewmodel.TariffViewModel
import com.asig.casco.model.FormFieldState
import com.asig.casco.model.Insurance
import com.asig.casco.model.Person
import com.asig.casco.model.Tariff
import com.asig.casco.model.Vehicle
import com.asig.casco.screens.common.checkBox
import com.asig.casco.screens.common.dropDownMenu
import com.asig.casco.screens.destinations.HomeScreenDestination
import com.asig.casco.screens.skeleton.ScaffoldSkeleton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Destination(start = false)
@Composable
fun InsuranceFormScreen(
    navigator: DestinationsNavigator
) {

    val tariffViewModel : TariffViewModel = hiltViewModel()
    val insuranceViewModel : InsuranceViewModel = hiltViewModel()
    var tariff : Tariff

    val tariffPrice by tariffViewModel.getTariffResult.collectAsState(initial = 0)

    LaunchedEffect(key1 = "loadInsurances") {
        insuranceViewModel.getInsuranceByEmail("johndoy1@mail.ru")
    }

    val retrofitTariff = RetrofitFactory().getRetrofitInstance()
    retrofitTariff.create(TariffApi::class.java)

    val firstName = remember { mutableStateOf(FormFieldState()) }
    val lastName = remember { mutableStateOf(FormFieldState()) }
    val IDNP = remember { mutableStateOf(FormFieldState()) }
    val phone = remember { mutableStateOf(FormFieldState()) }
    val additionalIDNP = remember { mutableStateOf(FormFieldState()) }
    val additionalFirstName = remember { mutableStateOf(FormFieldState()) }
    val additionalLastName = remember { mutableStateOf(FormFieldState()) }
    val additionalPhone = remember { mutableStateOf(FormFieldState()) }
    val model = remember { mutableStateOf(FormFieldState()) }
    val make = remember { mutableStateOf(FormFieldState()) }
    val year = remember { mutableStateOf(FormFieldState()) }
    val price = remember { mutableStateOf(FormFieldState()) }
    val certificateNumber = remember { mutableStateOf(FormFieldState()) }
    val registrationNumber = remember { mutableStateOf(FormFieldState()) }
    val startDay = remember { mutableStateOf(LocalDate.now()) }
    val endDay = remember { mutableStateOf(startDay.value.plusYears(1))}

    val screenNumber = remember {
        mutableStateOf(1)
    }

    val openDialog = remember { mutableStateOf(false) }
    val showConfirmDialog = remember { mutableStateOf(false) }

    val selectedType = remember {
        mutableStateOf(0)
    }
    val showAdditionalPerson = remember { mutableStateOf(false) }
    val acceptDataProcessing = remember { mutableStateOf(false) }
    val acceptTerms = remember { mutableStateOf(false) }

    ScaffoldSkeleton(navigator = navigator, titleBar = "Asigurare  nouă") {


        Column(modifier = Modifier.padding(45.dp, 10.dp, 45.dp, 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(modifier = Modifier.height(15.dp))

            if(screenNumber.value == 1) {
                PersonDetails(firstName, lastName, phone, IDNP, additionalFirstName, additionalLastName,additionalIDNP, showAdditionalPerson)
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
                VehicleDetails(selectedType, make, model, year, price, certificateNumber, registrationNumber)
                checkBox("Accept termenii si conditiile", acceptDataProcessing)
                checkBox("Accept prelucrarea datelor cu caracter personal", acceptTerms)
                Spacer(modifier = Modifier.height(15.dp))

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
                            if(year.value.value.text.isNotBlank() && price.value.value.text.isNotBlank()) {

                                tariff = Tariff(
                                    insurer = "moldasig",
                                    insuranceType = "casco",
                                    vehicleType =  getCarTypesList()[selectedType.value],
                                    age = year.value.value.text.toInt(),
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
                        enabled = year.value.value.text.isNotBlank() && price.value.value.text.isNotBlank()
                    ) {
                        Text(text = "Verifică prețul")
                    }
                }
                if(tariffPrice != 0 && price.value.value.text.isNotBlank()) {
                    Divider(modifier = Modifier.
                        padding(0.dp, 20.dp),
                        color = Color.Gray, thickness = 1.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ("%.2f".format(tariffPrice.toDouble() * price.value.value.text.toDouble() / 100)) + " lei",
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentWidth(Alignment.Start),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                if (firstName.value.value.text.isNotBlank() &&
                                    lastName.value.value.text.isNotBlank() &&
                                    IDNP.value.value.text.isNotBlank() &&
                                    phone.value.value.text.isNotBlank() &&
                                    (!showAdditionalPerson.value ||
                                            (additionalFirstName.value.value.text.isNotBlank() &&
                                                    additionalLastName.value.value.text.isNotBlank() &&
                                                    additionalIDNP.value.value.text.isNotBlank())) &&
                                    make.value.value.text.isNotBlank() &&
                                    model.value.value.text.isNotBlank() &&
                                    year.value.value.text.isNotBlank() &&
                                    price.value.value.text.isNotBlank() &&
                                    certificateNumber.value.value.text.isNotBlank() &&
                                    registrationNumber.value.value.text.isNotBlank()) {

                                    val vehicle = Vehicle(getCarTypesList()[selectedType.value],
                                    model.value.value.text,
                                    make.value.value.text,
                                    year.value.value.text.toInt(),
                                    price.value.value.text.toFloat(),
                                    certificateNumber.value.value.text,
                                    registrationNumber.value.value.text)

                                    val persons = ArrayList<Person>()

                                    val person = Person (firstName.value.value.text,
                                        lastName.value.value.text,
                                        IDNP.value.value.text,
                                        phone.value.value.text
                                    )
                                    persons.add(person)

                                    if(showAdditionalPerson.value){
                                        val person1 = Person (additionalFirstName.value.value.text,
                                            additionalLastName.value.value.text,
                                            additionalIDNP.value.value.text,
                                            additionalPhone.value.value.text
                                        )
                                        persons.add(person1)
                                    }

                                    val insurance = Insurance(vehicle,
                                        persons,
                                        "casco",
                                        "%.2f".format(tariffPrice.toDouble() * price.value.value.text.toDouble() / 100),
                                        "moldasig",
                                        "DEFAULT",
                                        startDay.value.format(DateTimeFormatter.ISO_DATE),
                                        endDay.value.format(DateTimeFormatter.ISO_DATE),
                                        tariffPrice.toFloat() * price.value.value.text.toFloat() / 100
                                    )
                                    insuranceViewModel.saveInsurance(insurance)
                                    // Add your API request code here

                                    // Show confirmation dialog
/*                                    showConfirmDialog.value = true*/
                                    openDialog.value = true
                                }
                            },
                            shape = RoundedCornerShape(5.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                        ) {
                            Text(text = "Procură")
                        }

                        if (openDialog.value) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog.value = false
                                },
                                title = {
                                    Text(text = "Succes")
                                },
                                text = {
                                    Text("Asigurarea a fost procurată cu succes")
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            openDialog.value = false
                                            navigator.navigate(HomeScreenDestination)
                                        }) {
                                        Text("Ok")
                                    }
                                }
                            )
                        }

/*                        if (showConfirmDialog.value) {
                            ConfirmPurchaseDialog(
                                openDialog = openDialog,
                                onConfirm = {
                                    // Handle confirmation here
                                    openDialog.value = false
                                    showConfirmDialog.value = false


                                },
                                onDismiss = {
                                    // Handle dismissal here
                                    openDialog.value = false
                                    showConfirmDialog.value = false
                                }
                            )
                        }*/
                    }

                }

            }
            

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetails(
    firstName : MutableState<FormFieldState>,
    lastName : MutableState<FormFieldState>,
    phone : MutableState<FormFieldState>,
    IDNP : MutableState<FormFieldState>,
    aditionalFirstName: MutableState<FormFieldState>,
    aditionalLastName: MutableState<FormFieldState>,
    aditionalIDNP: MutableState<FormFieldState>,
    showAdditionalPerson: MutableState<Boolean>
){

    CustomOutlinedTextField(
        value = firstName,
        label = "Prenume",
        onValueChange = { firstName.value = firstName.value.copy(value = it, touched = true) },
        isError = firstName.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = lastName,
        label = "Nume",
        onValueChange = { lastName.value = lastName.value.copy(value = it, touched = true) },
        isError = lastName.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = IDNP,
        label = "IDNO/IDNP",
        onValueChange = { IDNP.value = IDNP.value.copy(value = it, touched = true) },
        isError = IDNP.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )

    Spacer(modifier = Modifier.height(15.dp))

    CustomOutlinedTextField(
        value = phone,
        label = "Număr de telefon",
        onValueChange = { phone.value = phone.value.copy(value = it, touched = true) },
        isError = phone.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
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
            value = aditionalFirstName.value.value,
            onValueChange = { aditionalFirstName.value = aditionalFirstName.value.copy(value = it, touched = true) },
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            label = { Text(text = "Nume(șofer adițional)") },
            value = aditionalLastName.value.value,
            onValueChange = { aditionalLastName.value = aditionalLastName.value.copy(value = it, touched = true) },
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            label = { Text(text = "IDNO/IDNP(șofer adițional)") },
            value = aditionalIDNP.value.value,
            onValueChange = { aditionalIDNP.value = aditionalIDNP.value.copy(value = it, touched = true) },
        )
    }

    Spacer(modifier = Modifier.height(15.dp))

}
@Composable
fun VehicleDetails(
    type: MutableState<Int>,
    make: MutableState<FormFieldState>,
    model: MutableState<FormFieldState>,
    year: MutableState<FormFieldState>,
    price: MutableState<FormFieldState>,
    certificateNumber: MutableState<FormFieldState>,
    registrationNumber: MutableState<FormFieldState>
    ){

    Spacer(modifier = Modifier.height(15.dp))

    dropDownMenu(getCarTypesList(), label = "Tipul vehicului", selectedIndex = type)
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = make,
        label = "Marca",
        onValueChange = { make.value = make.value.copy(value = it, touched = true) },
        isError = make.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = model,
        label = "Model",
        onValueChange = { model.value = model.value.copy(value = it, touched = true) },
        isError = model.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = year,
        label = "Vârsta automobilului(ani)",
        onValueChange = {
            if (it.text.isFloat()) { // Check if the input is a valid float
                year.value = year.value.copy(value = it, touched = true)
            }
        },
        isError = year.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
    // ... rest o
/*    dropDownMenu(getCurrencyList(), label = "Model", selectedIndex = currency)*/
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = price,
        label = "Prețul pe piață",
        onValueChange = {
            if (it.text.isFloat() || it.text.isEmpty()) { // Check if the input is a valid float
                price.value = price.value.copy(value = it, touched = true)
            }
        },
        isError = price.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = certificateNumber,
        label = "Numărul certificatului de înmatriculare",
        onValueChange = { certificateNumber.value = certificateNumber.value.copy(value = it, touched = true) },
        isError = certificateNumber.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )
    Spacer(modifier = Modifier.height(15.dp))
    CustomOutlinedTextField(
        value = registrationNumber,
        label = "Număr de înregistrare",
        onValueChange = { registrationNumber.value = registrationNumber.value.copy(value = it, touched = true) },
        isError = registrationNumber.value.error,
        errorMessage = "Acest câmp nu poate fi lăsat liber!",
        hasInteracted = remember { mutableStateOf(false) }
    )
    Spacer(modifier = Modifier.height(15.dp))
//    CustomDatePicker(selectedEndDay, "Alegeți ultima zi a asigurării")
}

fun String.isFloat(): Boolean {
    return toFloatOrNull() != null
}

fun getCarTypesList(): ArrayList<String>{
    return arrayListOf("Autoturism", "Autobuz", "Autocamion, camion pentru semiremorca", "Vehicul agricol", "Troleibuz", "Remorcă / semiremorcă", "Utilaj suplimentar pentru vehicule")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: MutableState<FormFieldState>,
    label: String,
    onValueChange: (TextFieldValue) -> Unit,
    isError: Boolean,
    errorMessage: String,
    hasInteracted: MutableState<Boolean>,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        value = value.value.value,
        onValueChange = {
            value.value = value.value.copy(value = it, touched = true)
            hasInteracted.value = true
            onValueChange(it)  // Call the onValueChange function passed as a parameter
        },
        label = { Text(label) },
        isError = isError && hasInteracted.value,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
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
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}


@Composable
fun ConfirmPurchaseDialog(
    openDialog: MutableState<Boolean>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back button
                // This may also be where you want to reset the state of the dialog to its initial state
                onDismiss()
            },
            title = {
                Text(text = "Confirmati cumparatura")
            },
            text = {
                Text("Sunteti siguri ca vreti sa procurati asigurarea?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Change the dialog state to close it
                        // Perform any other actions when the user confirms
                        onConfirm()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the dialog state to close it
                        // Perform any other actions when the user dismisses
                        onDismiss()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}