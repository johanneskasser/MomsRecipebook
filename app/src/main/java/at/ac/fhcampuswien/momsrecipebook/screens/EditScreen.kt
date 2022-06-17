package at.ac.fhcampuswien.momsrecipebook.screens

import SimpleTopAppBar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Ingredient
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import java.text.SimpleDateFormat
import java.util.*

fun filterRecipe2(id: String?, recipe: List<Recipe>): Recipe {
    return recipe.filter { it.id == id }[0]
}
@Composable
fun EditScreen(
    navController: NavController = rememberNavController(),
    id : String?,
    viewModel: AddRecipeViewModel
){
    val recipe = filterRecipe2(id = id, recipe = viewModel.addedrecipes)
    Scaffold(topBar = {

        SimpleTopAppBar(arrowBackClicked = {navController.popBackStack()}) {
            Text(text = "Edit the Recipe: ${recipe.title}")
        }
    }){
        recipe.id?.let { it1 -> EditRecipe(navController = navController, addRecipeViewModel = viewModel, ingredients = viewModel.addedingredient, links = viewModel.addedlinks, author = recipe.author, remrecipe = recipe, id = id) }
    }
}

@Composable
fun EditRecipe(navController: NavController, addRecipeViewModel: AddRecipeViewModel, ingredients: List<String>, links: List<String>, author: String?, addNewRecipe: (Recipe) -> (Unit) = {} ,remrecipe: Recipe, id: String?){

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Edit The Recipe",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primaryVariant
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

        var cooktime by remember { mutableStateOf("") }

        OutlinedTextField(
            value = cooktime,
            onValueChange = { value -> cooktime = value },
            label = { Text(text = "Cooking Time") }
        )

        var quantity by remember { mutableStateOf("")}
        var unit by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(50.dp),
                value = quantity,
                onValueChange = { value -> quantity = value },
                label = { Text(text = "Quantity") }
            )
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(50.dp),
                value = unit,
                onValueChange = { value -> unit = value },
                label = { Text(text = "Unit") }
            )
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(100.dp),
                value = name,
                onValueChange = { value -> name = value },
                label = { Text(text = "Name") }
            )
        }
        Row {
            IconButton(
                onClick = {
                    val newIngredient = Ingredient(quantity, unit, name).toString()
                    addRecipeViewModel.addingredient(newIngredient)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
            IconButton(
                onClick = {
                    addRecipeViewModel.removealling(ingredients)
                }){
                Icon(imageVector = Icons.Default.Clear, contentDescription = "remove")
            }

        }
        var link1 by remember { mutableStateOf("") }
        var link2 by remember { mutableStateOf("") }
        var link3 by remember { mutableStateOf("") }

        Row(Modifier.padding(3.dp)) {
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(50.dp),
                value = link1,
                onValueChange = { value -> link1 = value },
                label = { Text(text = "Link") }
            )
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(50.dp),
                value = link2,
                onValueChange = { value -> link2 = value },
                label = { Text(text = "Link") }
            )
            OutlinedTextField(
                modifier = Modifier.defaultMinSize(50.dp),
                value = link3,
                onValueChange = { value -> link3 = value },
                label = { Text(text = "Link") }
            )

        }
        Row {
            IconButton(
                onClick = {
                    addRecipeViewModel.addlinks(link1)
                    addRecipeViewModel.addlinks(link2)
                    addRecipeViewModel.addlinks(link3)
                }){
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
            IconButton(
                onClick = {
                    addRecipeViewModel.removealllinks(links)
                }){
                Icon(imageVector = Icons.Default.Clear, contentDescription = "remove")
            }
        }




        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                val newRecipe = Recipe(id = id, title = title, description = description, time = cooktime, images = links, ingredients = ingredients, author = author)

                addRecipeViewModel.removeRecipe(remrecipe)
                addRecipeViewModel.addRecipe(newRecipe)
                addNewRecipe(newRecipe)
                navController.navigate(AppScreens.HomeScreen.name)
            }
        ) {
            Text(text = "Save")
        }

    }
}