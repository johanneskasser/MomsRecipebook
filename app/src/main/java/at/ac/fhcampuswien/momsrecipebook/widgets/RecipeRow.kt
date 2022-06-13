package at.ac.fhcampuswien.momsrecipebook.widgets

import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import at.ac.fhcampuswien.momsrecipebook.models.Recipe
import coil.compose.AsyncImage
import coil.size.OriginalSize
import coil.size.Scale
import coil.size.Scale.*

@Composable
fun RecipeRow(recipe: Recipe) {
    Card (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { Unit },
        shape = RoundedCornerShape(4.dp),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            LazyRow {
                for (image in recipe.images) {
                    item {
                        AsyncImage(model = image, contentDescription = "${recipe.title} ${recipe.id}" ,contentScale = ContentScale.Crop,modifier = Modifier.size(400.dp,150.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = recipe.title, style = MaterialTheme.typography.h5)
            Text(text = recipe.description, style = MaterialTheme.typography.h6)
        }
    }
}