package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class StatItem(val label: String, val value: Float, val color: Color)

@Composable
fun ProgressStatCard(
    title: String,
    stats: List<StatItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp), // Altura mínima
        shape = MaterialTheme.shapes.medium
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))

            stats.forEach { stat ->
                StatProgressRow(stat)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun StatProgressRow(stat: StatItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Cuadro de color redondeado
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(stat.color)
        )

        Spacer(Modifier.width(8.dp))

        // Etiqueta
        Text(
            stat.label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(2f)
        )

        Spacer(Modifier.width(8.dp))

        // Barra de progreso como extensión de RowScope
        StatProgressBar(stat)

        Spacer(Modifier.width(8.dp))

        // Valor porcentual
        Text(
            "${stat.value.toInt()}%",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

// Convertimos en extensión de RowScope para que Modifier.weight() funcione aquí
@Composable
private fun RowScope.StatProgressBar(stat: StatItem) {
    Box(
        Modifier
            .weight(1f)
            .height(8.dp)
    ) {
        // Fondo de la barra
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        )

        // Progreso
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(stat.value / 100f)
                .background(stat.color)
        )
    }
}
