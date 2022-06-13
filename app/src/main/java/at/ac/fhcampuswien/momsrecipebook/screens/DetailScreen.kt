package at.ac.fhcampuswien.momsrecipebook.screens

import SimpleTopAppBar
import TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import at.ac.fhcampuswien.momsrecipebook.widgets.RecipeRow
import at.ac.fhcampuswien.momsrecipebook.widgets.RemoveIcon
import java.lang.reflect.Modifier

fun filterRecipe(id: String?): Recipe{
    return getRecipes().filter { it.id == id }[0]
}

@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    id: String?,
    viewModel: AddRecipeViewModel
){
    val recipe = filterRecipe(id = id)
    
    Scaffold(
        topBar = { SimpleTopAppBar(arrowBackClicked = {navController.popBackStack()}){
            Text(text = recipe.title)
        } }
    ){
        MainContent(recipe = recipe, addRecipeViewModel = viewModel)
    }
}

@Composable
fun MainContent(recipe: Recipe, addRecipeViewModel: AddRecipeViewModel){
    Surface(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            RecipeRow(recipe = recipe){
                RemoveIcon(){r ->
                    if(addRecipeViewModel.isadded(r)){
                        addRecipeViewModel.removeRecipe(r)
                    }
                }
            }
        }
    }
}