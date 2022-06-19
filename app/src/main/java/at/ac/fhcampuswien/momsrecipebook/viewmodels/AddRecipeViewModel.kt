package at.ac.fhcampuswien.momsrecipebook.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import at.ac.fhcampuswien.momsrecipebook.models.Ingredient
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import java.nio.file.Files.exists

class AddRecipeViewModel : ViewModel() {

    private val _addedrecipes = mutableStateListOf<Recipe>()
    val addedrecipes: List<Recipe>
        get() = _addedrecipes

    fun addRecipe(recipe: Recipe) {
        if (!exists(recipe = recipe)) {
            _addedrecipes.add(recipe)
        }
    }

    fun removeRecipe(recipe: Recipe){
        _addedrecipes.remove(recipe)
    }

    private fun exists(recipe: Recipe) : Boolean {
        return _addedrecipes.any {r -> r.id == recipe.id}
    }

    fun removeAllRecipes() {
        if(addedrecipes.lastIndex > 0) {
            _addedrecipes.clear()
        }
    }

    fun isadded(recipe: Recipe): Boolean {
        return exists(recipe)
    }

    private val _addedingredients = mutableStateListOf<String>()
    val addedingredient: List<String>
        get() = _addedingredients

    fun addingredient(ingredient: String){
        _addedingredients.add(ingredient)
    }

    fun removealling(ingredient: List<String>) {
        _addedingredients.removeAll(ingredient)
    }

    private val _addedlinks = mutableStateListOf<String>()
    val addedlinks: List<String>
        get() = _addedlinks

    fun addlinks(link: String){
        _addedlinks.add(link)
    }

    fun removealllinks(link: List<String>) {
        _addedlinks.removeAll(link)
    }

    fun updateRecipe(recipe: Recipe) {
        addedrecipes.filter{ it.id == recipe.id }[0].title = recipe.title
        addedrecipes.filter{ it.id == recipe.id }[0].description = recipe.description
        addedrecipes.filter{ it.id == recipe.id }[0].images = recipe.images
        addedrecipes.filter{ it.id == recipe.id }[0].ingredients = recipe.ingredients
        addedrecipes.filter{ it.id == recipe.id }[0].time = recipe.time
    }


}