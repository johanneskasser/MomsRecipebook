package at.ac.fhcampuswien.momsrecipebook.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("_id")
    var _id: String?,

    @SerializedName("username")
    var username: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("jwt")
    var jwt: String?
)