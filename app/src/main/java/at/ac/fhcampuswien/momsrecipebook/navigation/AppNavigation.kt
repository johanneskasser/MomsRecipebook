package at.ac.fhcampuswien.momsrecipebook.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.MainActivity
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.screens.AddRecipeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.DetailScreen
import at.ac.fhcampuswien.momsrecipebook.screens.HomeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.LoginScreen
import at.ac.fhcampuswien.momsrecipebook.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()


    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.name) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController, onLoginClick = {email, password -> authViewModel.signIn(email = email, password = password) { MainActivity().signIn(email, password, navController) }})
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