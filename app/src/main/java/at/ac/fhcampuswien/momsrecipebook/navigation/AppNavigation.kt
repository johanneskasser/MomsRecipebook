package at.ac.fhcampuswien.momsrecipebook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.screens.AddRecipeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.DetailScreen
import at.ac.fhcampuswien.momsrecipebook.screens.HomeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.name) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen()
        }
        composable(AppScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(AppScreens.DetailScreen.name){
            DetailScreen(navController = navController)
        }
        composable(AppScreens.AddRecipeScreen.name){
            AddRecipeScreen(navController = navController)
        }
    }
}