package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun BarChartCard(
    title: String,
    labels: List<String>,
    values: List<Float>,
    modifier: Modifier = Modifier,
    color: Color
) {
    // Modelo Vico: index -> value
    val entries = values.mapIndexed { i, v -> entryOf(i.toFloat(), v) }
    val model: ChartEntryModel = entryModelOf(entries)

    Card(modifier = modifier, shape = MaterialTheme.shapes.medium) {
        Column(Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))
            Chart(
                chart = columnChart(),
                model = model,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                labels.forEach { label ->
                    Text(label, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
