
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import at.ac.fhcampuswien.momsrecipebook.ui.theme.Purple700

@Composable
fun TopBar(title: String, navController: NavController, content : @Composable () -> Unit = {}) {
    TopAppBar(backgroundColor = Purple700,elevation = 5.dp) {
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = title, style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun SimpleTopAppBar(arrowBackClicked: () -> Unit = {}, content: @Composable () -> Unit){ //Klasse für eine kleinere Topappbar mit arrowback für Screens außer Home
    TopAppBar(elevation = 3.dp) {
        Row {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                modifier = Modifier.clickable {
                    arrowBackClicked()
                }
            )

            Spacer(modifier = Modifier.width(20.dp))

            content()
        }
    }
}