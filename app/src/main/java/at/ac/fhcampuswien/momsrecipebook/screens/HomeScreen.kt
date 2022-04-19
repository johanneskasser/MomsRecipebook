package at.ac.fhcampuswien.momsrecipebook.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    Scaffold(
        topBar = { TopBar(title = "HomeScreen", navController = navController)}
    ){}

}