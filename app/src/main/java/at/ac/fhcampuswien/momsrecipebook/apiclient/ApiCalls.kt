package at.ac.fhcampuswien.momsrecipebook.apiclient

import android.util.Log
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.models.User
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCalls {
    fun login(email: String, password: String, navController: NavController) {
        val apiInterface = RecipeBookAPI.create().login(email, password)

        apiInterface.enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.body()?.equals("Success") == true) {
                    navController.navigate(AppScreens.HomeScreen.name)
                } else {
                    Log.e("Fail at User Login", "User not logged in")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Fail at Call", "User")
            }
        })
    }
}
