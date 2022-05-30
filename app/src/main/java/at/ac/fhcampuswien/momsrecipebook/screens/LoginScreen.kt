package at.ac.fhcampuswien.momsrecipebook.screens

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.auth.EmailPasswordActivity
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens

@Composable
fun LoginScreen(navController: NavController, onLoginClick: (email: String, password: String) -> Unit) {
    Scaffold(
        topBar = { TopBar(title = "Login", navController = navController)}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            LoginForm(onLoginClick = {email, password ->  onLoginClick(email, password)}/*{ email, password -> auth.signIn(email = email, password = password, navController = navController)*/ )
        }
    }
}

@Composable
fun LoginForm(onLoginClick: (email : String, password: String) -> Unit) {
    val email = remember{(mutableStateOf(TextFieldValue()))}
    val password = remember{(mutableStateOf(TextFieldValue()))}
    val showPassword = remember { mutableStateOf(false)}
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TextField(
            value = email.value,
            onValueChange = {email.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {Text(text = "Email Address")},
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon")
            }
        )

        TextField(
            value = password.value,
            onValueChange = {password.value = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {Text(text = "Password")},
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(imageVector = Icons.Default.VpnKey, contentDescription = "PasswordIcon")
            },
            trailingIcon = {
                if(showPassword.value) {
                    IconButton(onClick = {showPassword.value = false}) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = {showPassword.value = true}) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if(showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true
        )

        Button(
            onClick = { onLoginClick(email.value.text, password.value.text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Sign In")
        }

        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = "Not registered yet?")
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = {/*TODO Add Route to Register Form*/}) {
                Text(text = "Register")
            }
        }



    }
}

