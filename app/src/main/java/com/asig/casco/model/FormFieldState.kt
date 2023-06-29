package com.asig.casco.model

import androidx.compose.ui.text.input.TextFieldValue

data class FormFieldState(
    var value: TextFieldValue = TextFieldValue(),
    var error: Boolean = false,
    var touched: Boolean = false
)
