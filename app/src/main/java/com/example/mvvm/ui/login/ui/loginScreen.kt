package com.example.mvvm.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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


@Preview(showBackground = true, showSystemUi = true)
@Composable // dibuja algo en la pantalla
fun loginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {// Es un contenedor que permite colocar elementos uno encima de otro, va a ocupar toda la pantalla, agrega margen interno (16 dp por todos lados)
        Login(Modifier.align(Alignment.Center))// el contenido de Login se centrara dentro del box
        // align solo tiene sentido si se aplica a un elemento dentro de un Box (o de otro contenedor que use alineación). Tiene sentido si el padre sabe interpretarlo
    }
}

@Composable
fun Login(modifier: Modifier) { // recibe un párametro "modifier" de tipo Modifier ( fun Saludar( nombre: String))
    Column(modifier = modifier) { // column es una función le pasamos el párametro modifier, fun saludar(nombre: String) { ... } saludar(nombre = "Ana")
        // Column se va a alinear en el centro Modifier.align(Alignment.Center) que es lo que queremos
        HeaderImage()
        Spacer(modifier = Modifier.padding(16.dp))
        // Siguiente componente
        EmailField()
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
fun EmailField() {
    TextField(
        value = "",
        onValueChange = {}, // parámetro lambda : una función que puedes pasar como parámetro sin darle nombre
        // función normal fun sumar(a: Int, b: Int): Int = a + b, función lambda val sumar = { a: Int, b: Int -> a + b }
        // lambda sin parámetros (() -> Unit) no recibe ningún argumento Unit → no devuelve nada
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), //En Compose se llama KeyboardOptions() con K mayúscula porque es un constructor de clase, no una función.
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(focusedTextColor = Color(0xFF1E2C79), focusedContainerColor = Color(
            0xFF34083A
        )
        )

    )
}


@Composable
fun HeaderImage() {
    Image(painterResource(R.drawable.ty), "header")

}