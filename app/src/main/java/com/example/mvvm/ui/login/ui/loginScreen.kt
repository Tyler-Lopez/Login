package com.example.mvvm.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import com.example.mvvm.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Preview(showBackground = true, showSystemUi = true)
@Composable // dibuja algo en la pantalla
fun loginScreen_Preview() {
    LoginScreen(
        viewModel = LoginViewModel(),
    )
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state2.collectAsStateWithLifecycle()
    val email = state.value.email

    // recibe un párametro "modifier" de tipo Modifier ( fun Saludar( nombre: String))
    Column(
        modifier = modifier
            .background(Color.Blue)
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
    ) { // column es una función le pasamos el párametro modifier, fun saludar(nombre: String) { ... } saludar(nombre = "Ana")
        // Column se va a alinear en el centro Modifier.align(Alignment.Center) que es lo que queremos
        HeaderImage()
        Spacer(modifier = Modifier.padding(16.dp))
        // Siguiente componente
        EmailField(
            value = email,
            onValueChange = { nuevaString ->

                // Acabamos de HACER un evento :O
                val event = LoginViewEvent
                    .ChangedEmailText(nuevaString = nuevaString)

                // Ahora tengo que decirle al view model sobre el evento
                viewModel.onEvent(event)

            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Password()
        Spacer(modifier = Modifier.padding((8.dp)))
        forgotPassword()
    }

}

@Composable
fun forgotPassword() {
    Text(
        text = "olvidaste la contraseña",
        modifier = Modifier.clickable {},
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold, //Negritas
        color = Color(
            0xFF0A70C0
        ),

        )

}

@Composable
fun Password() {
    TextField(
        "",
        onValueChange = {},
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
    )
}

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = onValueChange, // parámetro lambda : una función que puedes pasar como parámetro sin darle nombre
        // función normal fun sumar(a: Int, b: Int): Int = a + b, función lambda val sumar = { a: Int, b: Int -> a + b }
        // lambda sin parámetros (() -> Unit) no recibe ningún argumento Unit → no devuelve nada
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), //En Compose se llama KeyboardOptions() con K mayúscula porque es un constructor de clase, no una función.
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1E2C79), focusedContainerColor = Color(
                0xFF34083A
            )
        )

    )
}


@Composable
fun HeaderImage() {
    Image(painterResource(R.drawable.ty), "header")

}