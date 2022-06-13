package at.ac.fhcampuswien.momsrecipebook.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhcampuswien.momsrecipebook.MainActivity
import at.ac.fhcampuswien.momsrecipebook.auth.EmailPasswordActivity
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.screens.AddRecipeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.DetailScreen
import at.ac.fhcampuswien.momsrecipebook.screens.HomeScreen
import at.ac.fhcampuswien.momsrecipebook.screens.LoginScreen
import at.ac.fhcampuswien.momsrecipebook.viewmodel.AuthViewModel
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val addRecipeViewModel: AddRecipeViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(navController = navController, onLoginClick = {email, password -> authViewModel.signIn(email = email, password = password) { MainActivity().signIn(email, password, navController) }})
        }
        composable(AppScreens.HomeScreen.name){
            HomeScreen(navController = navController, addRecipeViewModel)
        }
        composable(route = AppScreens.DetailScreen.name+"/{id}",
                arguments = listOf(navArgument(name = "id"){
                    type = NavType.StringType
        })){navBackStackEntry ->

            DetailScreen(navController = navController,
                id = navBackStackEntry.arguments?.getString("id"), addRecipeViewModel)
        }
        composable(AppScreens.AddRecipeScreen.name){
            AddRecipeScreen(navController = navController, addRecipeViewModel)
        }
    }
}