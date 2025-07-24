// EventHistoryCard.kt
package mx.edu.uttt.dashboard.Screens.Components.UserProfile
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class EventItem(
    val datetime: String,
    val type: String,
    val value: String,
    val location: String,
    val action: String
)

@Composable
fun EventHistoryCard(
    modifier: Modifier = Modifier,
    title: String = "Historial de Eventos del Turno / Semana",
    events: List<EventItem>
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            events.forEach { e ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(e.datetime, modifier = Modifier.weight(1f))
                    Text(e.type, modifier = Modifier.weight(1f))
                    Text(e.value, modifier = Modifier.weight(1f))
                    Text(e.location, modifier = Modifier.weight(1f))
                    Text(e.action, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
