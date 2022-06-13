package at.ac.fhcampuswien.momsrecipebook.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
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

    fun isadded(recipe: Recipe): Boolean {
        return exists(recipe)
    }
}