package com.example.mvvm.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import com.example.mvvm.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch


@Composable // dibuja algo en la pantalla
fun LoginScreen(viewModel: LoginViewModel) { //principios
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {// Es un contenedor que permite colocar elementos uno encima de otro, va a ocupar toda la pantalla, agrega margen interno (16 dp por todos lados)
        Login(
            Modifier.align(Alignment.Center),
            viewModel
        )// el contenido de Login se centrara dentro del box
        // align solo tiene sentido si se aplica a un elemento dentro de un Box (o de otro contenedor que use alineación). Tiene sentido si el padre sabe interpretarlo
    }
}

@Composable
fun Login(
    modifier: Modifier,
    viewModel: LoginViewModel
) {
    val email by viewModel.email.collectAsStateWithLifecycle() // recibe un párametro "modifier" de tipo Modifier ( fun Saludar( nombre: String))
    val password by viewModel.password.collectAsStateWithLifecycle()
    val enabled by viewModel.enabled.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val coroutine = rememberCoroutineScope()

    if (loading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) { // column es una función le pasamos el párametro modifier, fun saludar(nombre: String) { ... } saludar(nombre = "Ana")
            // Column se va a alinear en el centro Modifier.align(Alignment.Center) que es lo que queremos
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            // Siguiente componente
            EmailField(email) { viewModel.onLoginChanged(it, password) }
            Spacer(modifier = Modifier.height(8.dp))
            Password(password) { viewModel.onLoginChanged(email, it) }
            Spacer(modifier = Modifier.padding((8.dp)))
            ForgotPassword(Modifier.align(Alignment.End)) // column organiza a lo que hay debajo, la propiedad align() de los hijos de una Column espera un Alignment.Horizontal, no un Alignment completo (que es bidimensional).
            Spacer(modifier = Modifier.padding((16.dp)))
            LoginButton(enabled ) {
                coroutine.launch {
                    viewModel.pressButton()

                }
            }
        }


    }

}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "olvidaste la contraseña?",
        modifier = modifier.clickable {},
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold, //Negritas
        color = Color(
            0xFF0A70C0
        ),

        )

}

@Composable
fun Password(password: String, onTextFieldChange: (String) -> Unit) {
    // val passwordState = viewModel.password. collectAsStateWithLifecycle()
    // val password = passwordState.value
    TextField(
        password,
        onValueChange = { onTextFieldChange(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        visualTransformation = PasswordVisualTransformation() // Investigar
    )
}

@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) { //
    // se comporta como un String que siempre refleja el último estado automáticamente.
    // convierte a StateFlow en un State<String> de Compose.
    // Convierte ese Flow en un State observable por la UI, y además se detiene automáticamente cuando la pantalla no está visible (gracias al lifecycle).
    // val emailFlow = viewModel.email // StateFlow<String>
    //var email = emailFlow.value // valor inicial
    //lifecycleScope.launch {
    //    repeatOnLifecycle(Lifecycle.State.STARTED) {
    //        emailFlow.collect { newEmail ->
    //            email = newEmail1
    //        }
    //    }
    //}
    // Algo interesante es que le dice a la UI que observer el flujo de email durante el ciclo de vida

    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) }, // parámetro lambda : una función que puedes pasar como parámetro sin darle nombre
        // función normal fun sumar(a: Int, b: Int): Int = a + b, función lambda val sumar = { a: Int, b: Int -> a + b }
        // lambda sin parámetros (() -> Unit) no recibe ningún argumento Unit → no devuelve nada
        // onValueChange es un parámetro del TextField, y su tipo es una función lambda, que va a recibir new value y regresara view.Model.onEmailChanged(newValue)
        // a view model le pasa el texto nuevo
        // una función que se ejecuta cada vez que el usuario escribe algo. onValueChange = { newText -> text = newText },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), //En Compose se llama KeyboardOptions() con K mayúscula porque es un constructor de clase, no una función.
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1E2C79), focusedContainerColor = Color(0xFFE7DCE8)
        )

    )
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(painterResource(R.drawable.ty), "header", modifier = modifier.size(300.dp))

}

@Composable
fun LoginButton(enable: Boolean, onCLickChanged: () -> Unit) {
    Button(
        onClick = onCLickChanged, // aquí decides que pasará cuando haga click, debes guardar el estado en una sola variable, en este caso texto
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            Color(0xFFF5683C),
            disabledContainerColor = Color(0xFFE7CBCB),
            contentColor = Color(0xFFFFFFFF),
        ), enabled = enable
    ) { Text("Iniciar sesión") }

}
