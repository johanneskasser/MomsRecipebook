package at.ac.fhcampuswien.momsrecipebook.apiclient

import android.util.Log
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.User
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.viewmodel.AuthViewModel
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCalls {
    private val apiInterface = RecipeBookAPI.create()

    fun login(email: String, password: String, navController: NavController, onFailure: (String) -> Unit = {}, onSuccess: (User) -> Unit = {}) {
        val user = User(_id = null, username = null, email = email, password = password, jwt = null)

        apiInterface.login(user).enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    navController.navigate(AppScreens.HomeScreen.name)
                    user.username = response.body()?.username
                    user._id = response.body()?._id
                    user.password = null.toString()
                    user.jwt = response.headers().get("Set-Cookie")
                    onSuccess(user)
                } else {
                    Log.e("Fail at User Login", "User not logged in")
                    onFailure("Wrong Username or Password!")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Fail at Call", "User")
                onFailure("Wrong Username or Password!")
            }
        })
    }

    fun getRecipes(userID: String, addRecipeViewModel: AddRecipeViewModel, onFailure: (String) -> Unit) {
        apiInterface.getRecipes(userID = userID).enqueue( object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if(response.isSuccessful) {
                    val recipes : List<Recipe>? = response.body()
                    if (recipes != null) {
                        for(recipe in recipes) {
                            addRecipeViewModel.addRecipe(recipe = recipe)
                        }
                    }
                } else {
                    onFailure("Failed to load Users Recipes")
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }

        })
    }

    fun logout(navController: NavController, authViewModel: AuthViewModel) {
        apiInterface.logout().enqueue( object: Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful) {
                    authViewModel.logout()
                    navController.navigate(AppScreens.LoginScreen.name)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }

        })
    }
}
