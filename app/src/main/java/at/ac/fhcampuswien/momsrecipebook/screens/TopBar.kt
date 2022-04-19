package at.ac.fhcampuswien.momsrecipebook.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.ui.theme.Purple700

@Composable
fun TopBar(title: String, navController: NavController) {
    TopAppBar(backgroundColor = Purple700, elevation = 5.dp) {
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = title, style = MaterialTheme.typography.subtitle1)
        }
    }
}