// MapCard.kt
package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import mx.edu.uttt.dashboard.R

data class MapDot(val xRatio: Float, val yRatio: Float, val color: Color)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCard(
    title: String,
    stateName: String,
    svgRes: Int,
    dots: List<MapDot>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = { expanded = false }
    ) {
        Column(Modifier.fillMaxWidth()) {
            // Encabezado
            MapCardHeader(title, stateName) { expanded = true }

            // Línea divisoria
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))

            // Contenido: mapa y leyenda
            MapCardContent(svgRes, dots)
        }

        // Menú desplegable
        MapCardDropdownMenu(expanded) { expanded = false }
    }
}

@Composable
private fun MapCardHeader(title: String, stateName: String, onMenuClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text(
                stateName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        IconButton(onClick = onMenuClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "Opciones",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MapCardContent(svgRes: Int, dots: List<MapDot>) {
    Row(Modifier.fillMaxWidth().height(300.dp)) {
        // Mapa (75% del espacio)
        Box(Modifier.weight(3f).padding(16.dp)) {
            MapWithDots(svgRes, dots)
        }

        // Línea divisoria vertical
        Divider(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .padding(vertical = 16.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )

        // Leyenda (25% del espacio)
        MapLegend(modifier = Modifier.weight(1f).padding(16.dp))
    }
}

@Composable
private fun MapWithDots(svgRes: Int, dots: List<MapDot>) {
    var containerSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f))
            .onSizeChanged { containerSize = it.toSize() }
    ) {
        Image(
            painter = painterResource(id = svgRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        if (containerSize != androidx.compose.ui.geometry.Size.Zero) {
            Canvas(modifier = Modifier.matchParentSize()) {
                dots.forEach { dot ->
                    val center = Offset(
                        x = dot.xRatio * containerSize.width,
                        y = dot.yRatio * containerSize.height
                    )
                    drawCircle(
                        color = dot.color,
                        radius = 6.dp.toPx(),
                        center = center
                    )
                }
            }
        }
    }
}

@Composable
private fun MapLegend(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        LegendItem(color = Color(0xFF9CCC65), label = "Leve")
        Spacer(Modifier.height(8.dp))
        LegendItem(color = Color(0xFFFFB74D), label = "Media")
        Spacer(Modifier.height(8.dp))
        LegendItem(color = Color(0xFFE57373), label = "Alta")
        Spacer(Modifier.height(8.dp))
        LegendItem(color = Color(0xFFD32F2F), label = "Grave")
        Spacer(Modifier.height(8.dp))
        LegendItem(color = Color(0xFFB71C1C), label = "Muy Grave")
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(14.dp)
                .background(color, shape = MaterialTheme.shapes.small)
        )
        Spacer(Modifier.width(12.dp))
        Text(label, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun MapCardDropdownMenu(expanded: Boolean, onDismiss: () -> Unit) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.background(
            MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        DropdownMenuItem(
            text = { Text("Opción 1") },
            onClick = onDismiss
        )
        DropdownMenuItem(
            text = { Text("Opción 2") },
            onClick = onDismiss
        )
        DropdownMenuItem(
            text = { Text("Opción 3") },
            onClick = onDismiss
        )
    }
}