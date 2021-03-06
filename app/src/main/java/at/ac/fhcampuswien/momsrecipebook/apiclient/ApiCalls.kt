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

    fun login(
        email: String,
        password: String,
        navController: NavController,
        onFailure: (String) -> Unit = {},
        onSuccess: (User) -> Unit = {}
    ) {
        val user = User(_id = null, username = null, email = email, password = password, jwt = null)

        apiInterface.login(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
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
                onFailure("Internet Connection lost, please try again!")
            }
        })
    }

    fun register(
        username: String,
        email: String,
        password: String,
        navController: NavController,
        onResponse: (String) -> (Unit)
    ) {
        val user =
            User(_id = null, username = username, email = email, password = password, jwt = null)

        apiInterface.register(user = user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    navController.navigate(AppScreens.LoginScreen.name)
                    onResponse("User successfully registered!")
                } else {
                    onResponse("User already registered!")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onResponse("Internet Connection Lost! Please try again!")
            }

        })
    }

    fun getRecipes(
        userID: String,
        addRecipeViewModel: AddRecipeViewModel,
        onFailure: (String) -> Unit
    ) {
        apiInterface.getRecipes(userID = userID).enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    val recipes: List<Recipe>? = response.body()
                    if (recipes != null) {
                        for (recipe in recipes) {
                            addRecipeViewModel.addRecipe(recipe = recipe)
                        }
                    }
                } else {
                    onFailure("No Recipes added yet!")
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                onFailure("Internet Connection Lost! Please try again!")
            }

        })
    }

    fun logout(navController: NavController, authViewModel: AuthViewModel) {
        apiInterface.logout().enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    authViewModel.logout()
                    navController.navigate(AppScreens.LoginScreen.name)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }

        })
    }

    fun createRecipe(
        recipe: Recipe,
        oldRecipe: Recipe?,
        addRecipeViewModel: AddRecipeViewModel,
        navController: NavController
    ) {
        apiInterface.createRecipe(recipe = recipe).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    val fullRecipe: Recipe? = response.body()
                    oldRecipe?.let { addRecipeViewModel.removeRecipe(recipe = oldRecipe) }
                    fullRecipe?.let {
                        addRecipeViewModel.removeRecipe(recipe = recipe); addRecipeViewModel.addRecipe(
                        fullRecipe
                    ); navController.navigate(AppScreens.HomeScreen.name)
                    }
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }

        })
    }

    fun editRecipe(
        recipe: Recipe,
        addRecipeViewModel: AddRecipeViewModel,
        navController: NavController,
        onResponse: (String) -> Unit
    ) {
        apiInterface.editRecipe(recipe = recipe).enqueue(object : Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    val updatedRecipe: Recipe? = response.body()
                    updatedRecipe?.let {
                        navController.navigate(AppScreens.HomeScreen.name)
                        addRecipeViewModel.updateRecipe(updatedRecipe)
                    }
                    onResponse("Recipe updated successfully!")
                } else {
                    onResponse("Recipe could not be found in Database!")
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                onResponse("Internet Connection Lost! Please try again!")
            }

        })
    }

    fun deleteRecipe(_id: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        apiInterface.deleteRecipe(_id = _id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    onSuccess("Recipe deleted successfully!")
                } else {
                    onSuccess("ERROR 404: Recipe not found!")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                onFailure("Internet Connection lost! Please try again!")
            }

        })
    }
}
