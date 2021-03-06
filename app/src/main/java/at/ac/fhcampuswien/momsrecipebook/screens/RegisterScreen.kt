package at.ac.fhcampuswien.momsrecipebook.screens

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.services.makeToast


@Composable
fun RegisterScreen(navController: NavController, onRegister: (String, String, String) -> Unit) {
    Scaffold(
        topBar = { TopBar(title = "Register", navController = navController) }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            RegisterForm(
                navController = navController,
                onRegister = { username, email, password -> onRegister(username, email, password) }
            )
        }
    }
}

@Composable
fun RegisterForm(navController: NavController, onRegister: (String, String, String) -> (Unit)) {
    val email = remember { (mutableStateOf(TextFieldValue())) }
    val password = remember { (mutableStateOf(TextFieldValue())) }
    val showPassword = remember { mutableStateOf(false) }
    val username = remember { (mutableStateOf(TextFieldValue())) }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(text = "Username") },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.VerifiedUser, contentDescription = "username name")
            }
        )

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(text = "Email Address") },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon")
            }
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(imageVector = Icons.Default.VpnKey, contentDescription = "PasswordIcon")
            },
            trailingIcon = {
                if (showPassword.value) {
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true
        )

        Button(
            onClick = {
                val digits = "0123456789"
                if(password.value.text.length > 10 && password.value.text.any {it in digits}) {
                    onRegister(username.value.text, email.value.text, password.value.text)
                } else {
                    makeToast(context = context, "Please provide a password longer than 10 Symbols containing digits!")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Register")
        }

        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = "Already registered?")
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { navController.navigate(AppScreens.LoginScreen.name) }) {
                Text(text = "Login")
            }
        }
    }

}


