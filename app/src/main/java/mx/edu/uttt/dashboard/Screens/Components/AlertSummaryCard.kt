package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class AlertItem(
    val employee: String,
    val action: String,
    val incident: String,
    val date: String,
    val severity: String
)

@Composable
fun AlertSummaryCard(
    title: String,
    alerts: List<AlertItem>,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))
            alerts.forEach { a ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(a.employee, style = MaterialTheme.typography.bodyMedium)
                    AssistChip(
                        onClick = { /* Acción al hacer clic */ },
                        label = { Text(a.action) },
                        colors = AssistChipDefaults.assistChipColors()
                    )
                    Text(a.incident, style = MaterialTheme.typography.bodyMedium)
                    Text(a.date, style = MaterialTheme.typography.bodySmall)
                    Text(a.severity, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
