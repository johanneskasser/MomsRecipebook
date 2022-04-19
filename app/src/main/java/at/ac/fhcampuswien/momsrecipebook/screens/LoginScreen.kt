package at.ac.fhcampuswien.momsrecipebook.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Title()
        Email()
        Password()
        SignInButton()
    }    
}

@Composable 
fun Title() {
    Text(
        text = "Login",
        modifier = Modifier
            .padding(14.dp)
    )
}

@Composable
fun Email() {
    val email = remember{(mutableStateOf(TextFieldValue()))}

    TextField(
        value = email.value,
        onValueChange = {email.value = it},
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        label = {Text(text = "Email Address")}
    )
}

@Composable
fun Password() {
    val password = remember{(mutableStateOf(TextFieldValue()))}

    TextField(
        value = password.value,
        onValueChange = {password.value = it},
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        label = {Text(text = "Password")}
    )
}

@Composable
fun SignInButton() {
    Button(
        onClick = {/*TODO*/},
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
        Text(text = "Sign In")
    }
}