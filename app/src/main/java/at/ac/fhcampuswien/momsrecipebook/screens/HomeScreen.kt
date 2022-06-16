package at.ac.fhcampuswien.momsrecipebook.screens

import TopBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import at.ac.fhcampuswien.momsrecipebook.models.getRecipes
import at.ac.fhcampuswien.momsrecipebook.navigation.AppScreens
import at.ac.fhcampuswien.momsrecipebook.viewmodels.AddRecipeViewModel
import at.ac.fhcampuswien.momsrecipebook.widgets.RecipeRow
import at.ac.fhcampuswien.momsrecipebook.widgets.RemoveIcon

@Composable
fun HomeScreen( navController: NavController = rememberNavController(), viewModel: AddRecipeViewModel){
    var showMenu by remember { mutableStateOf(false)}

    Scaffold(topBar = {
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
                    DropdownMenuItem(onClick = { navController.navigate(route = AppScreens.AddRecipeScreen.name) }) { //navigation zum FavoriteScreen
                        Row(modifier = Modifier.clickable { navController.navigate(AppScreens.AddRecipeScreen.name) }) {
                            Text(text = "AddRecipeScreen", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp))
                        }
                    }
                }
            }
        )
    }){
        MainContent(navController = navController, addRecipeViewModel = viewModel, recipes = viewModel.addedrecipes)
    }

}

@Composable
fun MainContent(navController: NavController, addRecipeViewModel: AddRecipeViewModel, recipes: List<Recipe>){
    LazyColumn{
        items(recipes) { recipe ->
            RecipeRow(recipe = recipe,
                onItemClick = {id -> navController.navigate(AppScreens.DetailScreen.name+"/$id")})
            {
                RemoveIcon(recipe){r ->
                    if(addRecipeViewModel.isadded(r)){
                        addRecipeViewModel.removeRecipe(r)
                    }
                }
            }
        }
    }
}