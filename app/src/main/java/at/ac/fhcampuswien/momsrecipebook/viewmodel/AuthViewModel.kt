package at.ac.fhcampuswien.momsrecipebook.viewmodel

import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.momsrecipebook.models.User

class AuthViewModel : ViewModel() {
    var signedInUser: User? = null

    fun signIn(user: User) {
        signedInUser = user
    }

    fun logout() {
        signedInUser = null
    }

    fun getUser() : User? {
        return signedInUser
    }
}