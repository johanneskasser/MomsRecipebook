package at.ac.fhcampuswien.momsrecipebook

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.auth.EmailPasswordActivity
import at.ac.fhcampuswien.momsrecipebook.navigation.AppNavigation
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.ui.theme.MomsRecipebookTheme
import at.ac.fhcampuswien.momsrecipebook.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        //TODO: Init ViewModel --> viewmodel.login()... Callback with signin

        setContent {
            MomsRecipebookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //TODO: Inject Viewmodel into AppNav
                    AppNavigation(auth)
                }
            }
        }
    }


    fun createAccount(email: String, password: String, navController: NavController) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, navController)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_LONG).show()
                }
            }
        // [END create_user_with_email]
    }

    fun signIn(email: String, password: String, navController: NavController) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user, navController)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END sign_in_with_email]
    }

    private fun sendEmailVerification() {
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(this) { task ->
                // Email Verification sent
            }
        // [END send_email_verification]
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("USER: ", "${auth.currentUser}")
            //reload()
        }
    }

    private fun reload() {
        TODO("Not yet implemented")
    }

    private fun logout() {
        Firebase.auth.signOut()
    }

    private fun updateUI(user: FirebaseUser?, navController: NavController) {
        navController.navigate(AppScreens.HomeScreen.name)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MomsRecipebookTheme {
        //AppNavigation(auth)
    }
}