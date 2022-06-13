package at.ac.fhcampuswien.momsrecipebook.screens

import SimpleTopAppBar
import TopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Ingredient
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddRecipeScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddRecipeViewModel
){
    Scaffold(topBar = {

        SimpleTopAppBar(arrowBackClicked = {navController.popBackStack()}) {
            Text(text = "Add a Recipe")
        }
    }){
        AddRecipe(addRecipeViewModel = viewModel)
    }
}

@Composable
fun AddRecipe(addRecipeViewModel: AddRecipeViewModel){

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Add a Recipe",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primaryVariant
        )

        var id by remember { mutableStateOf("") }

        OutlinedTextField(
            value = id,
            onValueChange = { value -> id = value },
            label = { Text(text = "ID") }
        )

        var title by remember { mutableStateOf("") }

        OutlinedTextField(
            value = title,
            onValueChange = { value -> title = value },
            label = { Text(text = "Title") }
        )

        var description by remember { mutableStateOf("") }

        OutlinedTextField(
            value = description,
            onValueChange = { value -> description = value },
            label = { Text(text = "Description") }
        )

        var author by remember { mutableStateOf("") }

        OutlinedTextField(
            value = author,
            onValueChange = { value -> author = value },
            label = { Text(text = "Author") }
        )


        /**Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
                val currentDate = sdf.format(Date())
                val newRecipe = Recipe(id,title,description,currentDate,author)

                addRecipeViewModel.addRecipe(newRecipe)
            }
        ) {
            Text(text = "Save")
        }
**/
    }
}

/**@Composable
fun AddRecipe(
    onSaveClick: (Recipe) -> Unit = {}
){
    getRecipes()
    Text(text = "Add a Recipe",
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.primaryVariant)

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { value -> text = value},
        label = { Text(text = "Note") }
    )

    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {
            if(text.isNotEmpty()){
                val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
                //val currentDate = sdf.format(Date())
                val newNote = Recipe(text,)

                onSaveClick(newNote)

                text = ""
            }

        }) {

        Text( text = "Save")
    }
}**/