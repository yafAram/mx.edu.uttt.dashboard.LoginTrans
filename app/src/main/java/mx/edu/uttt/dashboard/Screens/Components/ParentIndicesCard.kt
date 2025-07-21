// ParentIndicesCard.kt
package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ParentIndicesCard(
    modifier: Modifier = Modifier,
    fatigueLabels: List<String>,
    fatigueValues: List<Float>,
    pauseLabels: List<String>,
    pauseValues: List<Float>
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(Modifier.padding(16.dp)) {
            // Encabezado
            IndicesHeader()

            Spacer(Modifier.height(16.dp))

            // Gráficas
            IndicesCharts(fatigueLabels, fatigueValues, pauseLabels, pauseValues)
        }
    }
}

@Composable
private fun IndicesHeader() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text("Índices", style = MaterialTheme.typography.titleMedium)
        Text("+",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun IndicesCharts(
    fatigueLabels: List<String>,
    fatigueValues: List<Float>,
    pauseLabels: List<String>,
    pauseValues: List<Float>
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        BarChartCard(
            title = "Índice de fatiga",
            labels = fatigueLabels,
            values = fatigueValues,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary
        )
        BarChartCard(
            title = "Número de pausas activas",
            labels = pauseLabels,
            values = pauseValues,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.primary
        )
    }
}