package at.ac.fhcampuswien.momsrecipebook.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.widgets.RecipeRow

@Composable
fun HomeScreen(recipes: List<Recipe>, navController: NavController = rememberNavController()){
    Scaffold(
        topBar = { TopBar(title = "HomeScreen", navController = navController)}
    ){
        LazyColumn{
            items(recipes) { recipe ->
                RecipeRow(recipe = recipe)
            }
        }
    }
}