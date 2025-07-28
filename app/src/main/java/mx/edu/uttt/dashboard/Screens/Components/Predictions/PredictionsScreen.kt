import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Pantalla de Predicciones:
 * - Muestra prompts predefinidos recibidos desde el backend.
 * - Permite seleccionar uno y generar datos.
 * - Genera gráficas sencillas de barras y línea sin librerías externas.
 */
@Composable
fun PredictionsScreen(
    modifier: Modifier = Modifier,
    prompts: List<String>,
    onGenerate: suspend (String) -> List<Float>
) {
    var selectedPrompt by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var dataPoints by remember { mutableStateOf<List<Float>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Predicciones de Desempeño",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Selecciona un prompt:",
            style = MaterialTheme.typography.bodyMedium
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            prompts.forEach { prompt ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedPrompt = prompt },
                    colors = CardDefaults.cardColors(
                        containerColor = if (prompt == selectedPrompt) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = prompt,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Button(
            onClick = {
                selectedPrompt?.let { prompt ->
                    coroutineScope.launch {
                        isLoading = true
                        dataPoints = onGenerate(prompt)
                        isLoading = false
                    }
                }
            },
            enabled = selectedPrompt != null && !isLoading,
            modifier = Modifier.align(Alignment.End)
        ) {
            if (isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
            else Text("Generar")
        }

        if (dataPoints.isNotEmpty()) {
            SimpleCharts(data = dataPoints)
        }
    }
}

@Composable
fun SimpleCharts(
    data: List<Float>,
    modifier: Modifier = Modifier
) {
    // Encuentra el valor máximo para escalar
    val maxValue = (data.maxOrNull() ?: 1f).coerceAtLeast(1f)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Gráfica de Barras", style = MaterialTheme.typography.titleMedium)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            data.forEachIndexed { index, value ->
                Text("Punto ${index + 1}: ${"%.1f".format(value)}")
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                        .background(Color(0xFFE0E0E0))
                ) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(fraction = (value / maxValue).coerceIn(0f, 1f))
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        }

        Text("Gráfica de Línea", style = MaterialTheme.typography.titleMedium)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFF5F5F5))
        ) {
            val w = size.width
            val h = size.height
            val stepX = w / (data.size - 1).coerceAtLeast(1)
            val points = data.mapIndexed { i, v ->
                val x = i * stepX
                val y = h - (v / maxValue * h)
                Offset(x, y)
            }
            // Dibuja líneas
            points.zipWithNext().forEach { (a, b) ->
                drawLine(
                    Color(0xFF6200EE),
                    strokeWidth = 4f,
                    start = a,
                    end = b
                )
            }
            // Dibuja puntos
            points.forEach { point ->
                drawCircle(
                    Color(0xFF6200EE),
                    radius = 6f,
                    center = point
                )
            }
        }
    }
}
