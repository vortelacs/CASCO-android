package com.asig.casco.screens.assurance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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

    val tariffPrice by tariffViewModel.getTariffResult.collectAsState(initial = -1)
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

    val selectedType = remember {
        mutableStateOf(-1)
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
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            if(selectedType.value !=  -1 && year.value.text.isNotBlank() && price.value.text.isNotBlank()) {

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
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = selectedType.value !=  -1 && year.value.text.isNotBlank() && price.value.text.isNotBlank()
                    ) {
                        Text(text = "Verifică prețul")
                    }
                }
                }
                if(tariffPrice != 0 && price.value.text.isNotBlank()) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                            Text(text = (tariffPrice.toDouble() * price.value.text.toDouble()).toString())
                        }
                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                            Button(
                                onClick = {
                                },
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(text = "Procură")
                            }
                        }

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
/*    email : MutableState<TextFieldValue>,*/
    phone : MutableState<TextFieldValue>,
    IDNP : MutableState<TextFieldValue>,
    aditionalFirstName: MutableState<TextFieldValue>,
    aditionalLastName: MutableState<TextFieldValue>,
    aditionalIDNP: MutableState<TextFieldValue>,
    showAdditionalPerson: MutableState<Boolean>
){

    var emailError by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Prenume") },
        value = firstName.value,
        onValueChange = { firstName.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Nume") },
        value = lastName.value,
        onValueChange = { lastName.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "IDNO/IDNP") },
        value = IDNP.value,
        onValueChange = { IDNP.value = it },
    )
/*    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        value = email.value,
        onValueChange = {
            email.value = it
            emailError = !Patterns.EMAIL_ADDRESS.matcher(it.text).matches()
        },
        label = { Text("Email") },
        isError = emailError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )*/
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Număr de telefon") },
        value = phone.value,
        onValueChange = { phone.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )

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
    dropDownMenu(getCurrencyList(), label = "Model", selectedIndex = currency)
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Anul fabricației") },
        value = year.value,
        onValueChange = { year.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Nume și prenume(șofer adițional)") },
        value = certificateNumber.value,
        onValueChange = { certificateNumber.value = it },
    )
    Spacer(modifier = Modifier.height(15.dp))
    TextField(
        label = { Text(text = "Număr de înregistrare") },
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
            Text(text = "Mai departe")
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



