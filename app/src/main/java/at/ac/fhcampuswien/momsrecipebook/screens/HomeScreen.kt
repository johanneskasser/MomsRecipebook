package at.ac.fhcampuswien.momsrecipebook.screens

import TopBar
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.apiclient.ApiCalls
import at.ac.fhcampuswien.momsrecipebook.models.Ingredient
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import at.ac.fhcampuswien.momsrecipebook.widgets.EditIcon
import at.ac.fhcampuswien.momsrecipebook.widgets.RecipeRow
import at.ac.fhcampuswien.momsrecipebook.widgets.RemoveIcon

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    viewModel: AddRecipeViewModel,
    onLogoutEvent: (String) -> Unit = {},
    onRemoveClick: (Recipe) -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(
            navController = navController,
            onLogout = { onLogoutEvent("Logout") },
            addRecipeViewModel = viewModel,
            ingredients = viewModel.addedingredient,
            links = viewModel.addedlinks
        )
    }) {
        MainContent(
            navController = navController,
            addRecipeViewModel = viewModel,
            recipes = viewModel.addedrecipes,
            ingredients = viewModel.addedingredient,
            onRemoveClick = {recipe -> onRemoveClick(recipe) },
            links = viewModel.addedlinks
        )
    }

}

@Composable
fun MainContent(
    navController: NavController,
    addRecipeViewModel: AddRecipeViewModel,
    recipes: List<Recipe>,
    ingredients: List<String>,
    onRemoveClick: (Recipe) -> (Unit),
    links: List<String>
) {
    LazyColumn {
        items(recipes) { recipe ->
            RecipeRow(recipe = recipe,
                onItemClick = { id -> navController.navigate(AppScreens.DetailScreen.name + "/$id") })
            {
                Row {
                    EditIcon(recipe = recipe,
                        onEditClick = { id ->
                            navController.navigate(AppScreens.EditScreen.name + "/$id")
                            addRecipeViewModel.removealling(ingredients)
                            addRecipeViewModel.removealllinks(links)
                        })


                    RemoveIcon(recipe) { r ->
                        if (addRecipeViewModel.isadded(r)) {
                            onRemoveClick(r)
                            addRecipeViewModel.removeRecipe(r)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    navController: NavController,
    onLogout: (String) -> Unit = {},
    addRecipeViewModel: AddRecipeViewModel,
    ingredients: List<String>,
    links: List<String>
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Moms Recipe Book") },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    navController.navigate(route = AppScreens.AddRecipeScreen.name)
                    addRecipeViewModel.removealling(ingredients)
                    addRecipeViewModel.removealllinks(links)
                }) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add a Recipe",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Add Recipe", modifier = Modifier
                                .width(200.dp)
                                .padding(4.dp)
                        )
                    }
                }
                DropdownMenuItem(onClick = { onLogout("Logout") }) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Logout",
                            modifier = Modifier.padding(4.dp)
                        )
                        Text(
                            text = "Logout",
                            modifier = Modifier
                                .padding(4.dp)
                                .width(200.dp)
                        )
                    }
                }
            }
        }
    )
}