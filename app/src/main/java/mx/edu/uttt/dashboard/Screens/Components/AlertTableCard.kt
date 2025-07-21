package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlertTableCard(
    title: String,
    alerts: List<AlertItem>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))
            TableHeader()
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
            AlertTableRows(alerts)
        }
    }
}

@Composable
private fun TableHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        TableHeaderText("Empleado", 2f)
        TableHeaderText("Acciones", 1.5f)
        TableHeaderText("Incidente", 1.5f)
        TableHeaderText("Fecha", 1f)
        TableHeaderText("Gravedad", 1f)
    }
}

@Composable
private fun RowScope.TableHeaderText(text: String, weightFraction: Float) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .weight(weightFraction)
            .padding(end = 8.dp)
    )
}

@Composable
private fun AlertTableRows(alerts: List<AlertItem>) {
    // Usamos LazyColumn para mejor rendimiento con muchas filas
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp) // Altura máxima para no crecer indefinidamente
    ) {
        items(alerts) { alert ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Celda de empleado con avatar
                EmployeeCell(alert.employee, weight = 2f)

                // Celda de acciones con botón
                ActionCell(alert.action, weight = 1.5f)

                // Celda de incidente
                TableCellText(alert.incident, weight = 1.5f)

                // Celda de fecha
                TableCellText(alert.date, weight = 1f)

                // Celda de gravedad con indicador de color
                SeverityCell(alert.severity, weight = 1f)
            }
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        }
    }
}

@Composable
private fun RowScope.EmployeeCell(name: String, weight: Float) {
    Row(
        modifier = Modifier
            .weight(weight)
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Avatar",
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun RowScope.ActionCell(action: String, weight: Float) {
    Box(
        modifier = Modifier
            .weight(weight)
            .padding(end = 8.dp)
    ) {
        // Usamos un botón para la acción
        Button(
            onClick = { /* Acción al hacer clic */ },
            modifier = Modifier.wrapContentWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = action,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun RowScope.TableCellText(text: String, weight: Float) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .weight(weight)
            .padding(end = 8.dp)
    )
}

@Composable
private fun RowScope.SeverityCell(severity: String, weight: Float) {
    // Determinar el color según la gravedad
    val color = when (severity.toLowerCase()) {
        "leve" -> Color(0xFF4CAF50)     // Verde
        "media" -> Color(0xFFFF9800)     // Naranja
        "alta" -> Color(0xFFF44336)      // Rojo
        "grave" -> Color(0xFFB71C1C)     // Rojo oscuro
        else -> MaterialTheme.colorScheme.onSurface // Color por defecto
    }

    Row(
        modifier = Modifier
            .weight(weight)
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Punto indicador de color
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = severity,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}