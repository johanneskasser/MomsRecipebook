package at.ac.fhcampuswien.momsrecipebook.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.ac.fhcampuswien.momsrecipebook.apiclient.ApiCalls
import at.ac.fhcampuswien.momsrecipebook.screens.*
import at.ac.fhcampuswien.momsrecipebook.services.makeToast
import at.ac.fhcampuswien.momsrecipebook.viewmodel.AuthViewModel
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val addRecipeViewModel: AddRecipeViewModel = viewModel()
    val apiCalls = ApiCalls()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.name) {
        composable(AppScreens.LoginScreen.name) {
            LoginScreen(
                navController = navController,
                onLoginClick = { email, password ->
                    apiCalls.login(
                        email = email,
                        password = password,
                        navController = navController,
                        onFailure = { makeToast(context = context, message = "Login Failed!") },
                        onSuccess = { user ->
                            authViewModel.signIn(user)
                            user._id?.let { uid ->
                                apiCalls.getRecipes(
                                    userID = uid,
                                    addRecipeViewModel = addRecipeViewModel,
                                    onFailure = { errormessage -> makeToast(context = context, message = errormessage) })
                            }
                        })
                })
        }
        composable(AppScreens.RegisterScreen.name) {
            RegisterScreen(
                navController = navController,
                onRegister = { username, email, password ->
                    apiCalls.register(
                        username = username,
                        email = email,
                        password = password,
                        navController = navController,
                        onResponse = {message -> makeToast(context = context, message = message)}
                    )
                })
        }
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                addRecipeViewModel,
                onLogoutEvent = {
                    apiCalls.logout(
                        navController = navController,
                        authViewModel = authViewModel
                    )
                    addRecipeViewModel.removeAllRecipes()
                    authViewModel.logout()
                },
                onRemoveClick = { recipe ->
                    recipe.id?.let { it1 ->
                        apiCalls.deleteRecipe(it1,
                            onSuccess = { message ->
                                makeToast(
                                    context = context,
                                    message = message
                                )
                            },
                            onFailure = { message ->
                                makeToast(
                                    context = context,
                                    message = message
                                )
                            }
                        )
                    }
                }
            )
        }
        composable(
            route = AppScreens.DetailScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->

            DetailScreen(
                navController = navController,
                id = navBackStackEntry.arguments?.getString("id"), addRecipeViewModel
            )
        }
        composable(AppScreens.AddRecipeScreen.name) {
            AddRecipeScreen(
                navController = navController,
                viewModel = addRecipeViewModel,
                author = authViewModel.getUser()?._id,
                addNewRecipe = { recipe ->
                    apiCalls.createRecipe(
                        recipe = recipe,
                        oldRecipe = null,
                        addRecipeViewModel = addRecipeViewModel,
                        navController = navController
                    )
                })
        }
        composable(route = AppScreens.EditScreen.name + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            EditScreen(
                navController = navController,
                id = navBackStackEntry.arguments?.getString("id"),
                viewModel = addRecipeViewModel,
                addNewRecipe = { recipe, oldRecipe ->
                    apiCalls.editRecipe(
                        recipe = recipe,
                        addRecipeViewModel = addRecipeViewModel,
                        navController = navController,
                        onResponse = {message -> makeToast(context = context, message = message)}
                    )
                }
            )
        }
    }
}