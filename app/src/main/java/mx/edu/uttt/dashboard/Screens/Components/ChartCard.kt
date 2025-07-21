package mx.edu.uttt.dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun ChartCard(
    title: String,
    values: List<Float>,
    modifier: Modifier = Modifier
) {
    // Crear modelo de datos correctamente
    val entries = values.mapIndexed { index, value ->
        com.patrykandpatrick.vico.core.entry.entryOf(index.toFloat(), value)
    }
    val model: ChartEntryModel = entryModelOf(entries)

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))
            Chart(
                chart = columnChart(),
                model = model,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
    }
}