package at.ac.fhcampuswien.momsrecipebook.apiclient

import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.User
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RecipeBookAPI {

    @POST("login")
    fun login (@Body user: User) : Call<User>

    @GET("getRecipe")
    fun getRecipes(@Query("userID") userID : String): Call<List<Recipe>>

    @POST("logout")
    fun logout(): Call<Unit>

    @POST("createRecipe")
    fun createRecipe(@Body recipe: Recipe) : Call<Recipe>

    @POST("editRecipe")
    fun editRecipe(@Body recipe: Recipe): Call<Recipe>

    @POST("register")
    fun register (@Body user: User) : Call<User>

    @GET("deleteRecipe")
    fun deleteRecipe(@Query("_id") _id : String) : Call<Unit>

    companion object {
        var BASE_URL = "https://recipebookbkend.herokuapp.com/api/"

        fun gson() = GsonBuilder()
            .setLenient()
            .create()

        fun create() : RecipeBookAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RecipeBookAPI::class.java)
        }
    }
}