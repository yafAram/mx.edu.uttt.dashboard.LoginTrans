// ContactAvatars.kt
package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun ContactAvatars(
    avatars: List<Int>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 120.dp), // Altura mínima
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Contactos", style = MaterialTheme.typography.titleSmall)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ContactAvatarList(avatars)
                AddContactButton()
            }
        }
    }
}

@Composable
private fun ContactAvatarList(avatars: List<Int>) {
    Row {
        avatars.take(5).forEachIndexed { index, res ->
            Image(
                painter = painterResource(id = res),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .offset(x = (-8 * index).dp)
                    .zIndex((5 - index).toFloat()),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun AddContactButton() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.small),
        contentAlignment = Alignment.Center
    ) {
        Text("+",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
