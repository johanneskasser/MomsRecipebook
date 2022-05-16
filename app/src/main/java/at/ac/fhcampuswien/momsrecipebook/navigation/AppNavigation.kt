package at.ac.fhcampuswien.momsrecipebook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.auth.EmailPasswordActivity
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.screens.AddRecipeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.DetailScreen
import at.ac.fhcampuswien.momsrecipebook.screens.HomeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.LoginScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val auth = EmailPasswordActivity()

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.name) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController, auth = auth)
        }
        composable(AppScreens.HomeScreen.name){
            HomeScreen(navController = navController, recipes = getRecipes())
        }
        composable(AppScreens.DetailScreen.name){
            DetailScreen(navController = navController)
        }
        composable(AppScreens.AddRecipeScreen.name){
            AddRecipeScreen(navController = navController)
        }
    }
}