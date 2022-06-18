package at.ac.fhcampuswien.momsrecipebook.screens

import SimpleTopAppBar
import TopBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import at.ac.fhcampuswien.momsrecipebook.widgets.EditIcon
import at.ac.fhcampuswien.momsrecipebook.widgets.RecipeRow
import at.ac.fhcampuswien.momsrecipebook.widgets.RemoveIcon
import java.lang.reflect.Modifier

fun filterRecipe(id: String?, recipe: List<Recipe>): Recipe{
    return recipe.filter { it.id == id }[0]
}

@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    id: String?,
    viewModel: AddRecipeViewModel
){
    val recipe = filterRecipe(id = id, recipe = viewModel.addedrecipes)
    
    Scaffold(
        topBar = { SimpleTopAppBar(arrowBackClicked = {navController.popBackStack()}){
            Text(text = recipe.title)
        } }
    ){
        MainContent(navController = navController, recipe = recipe, addRecipeViewModel = viewModel, ingredients = viewModel.addedingredient)
    }
}

@Composable
fun MainContent(navController: NavController, recipe: Recipe, addRecipeViewModel: AddRecipeViewModel, ingredients: List<String>){
    var showMenu by remember { mutableStateOf(false) }

    Surface(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
           RecipeRow(recipe = recipe) {
               EditIcon(recipe = recipe,
                   onEditClick = {id -> navController.navigate(AppScreens.EditScreen.name+"/$id")
                            addRecipeViewModel.removealling(ingredients)})
           }
            AnimatedVisibility(visible = showMenu){
                Column {
                    Text(text = "Cooking Time: ${recipe.time}")
                    LazyColumn{
                        items(recipe.ingredients){ ingredient ->
                            Text(text = "Ingredients: ${ingredient}")
                        }
                    }
                }
            }
            Icon(imageVector =
            if (showMenu) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.KeyboardArrowUp,
                contentDescription = "expand",
                modifier = androidx.compose.ui.Modifier
                    .size(25.dp)
                    .clickable {
                        showMenu = !showMenu
                    },
                tint = Color.DarkGray)

        }
    }
}