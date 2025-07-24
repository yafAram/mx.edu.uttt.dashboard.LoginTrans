package mx.edu.uttt.dashboard.Screens.Components.UserProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PersonalIndicatorsCard(
    modifier: Modifier = Modifier,
    titles: List<String>,
    values: List<Float>
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Indicadores Personales", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))
            // Simulación de gráfico de barras
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                titles.indices.forEach { idx ->
                    val value = values[idx]
                    Column {
                        Text(titles[idx], fontSize = 12.sp)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                                .background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(fraction = (value / 100).coerceIn(0f, 1f))
                                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("${value.toInt()}%", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}
