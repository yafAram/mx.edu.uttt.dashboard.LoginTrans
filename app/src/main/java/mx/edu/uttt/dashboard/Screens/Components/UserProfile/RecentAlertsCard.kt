package mx.edu.uttt.dashboard.Screens.Components.UserProfile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

data class AlertData(val date: String, val message: String)

@Composable
fun RecentAlertsCard(
    modifier: Modifier = Modifier,
    title: String = "Alertas Recientes Críticas",
    alerts: List<AlertData>
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            alerts.forEach { alert ->
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFF8F8F8),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("${alert.date} - ${alert.message}")
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Close, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}