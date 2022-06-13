package at.ac.fhcampuswien.momsrecipebook.apiclient

import at.ac.fhcampuswien.momsrecipebook.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RecipeBookAPI {
    @FormUrlEncoded
    @POST("/")
    fun login (@Field("email") email: String, @Field("password") password: String) : Call<User>

    companion object {
        var BASE_URL = "https://recipebookbkend.herokuapp.com/api/"

        fun create() : RecipeBookAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RecipeBookAPI::class.java)
        }
    }
}