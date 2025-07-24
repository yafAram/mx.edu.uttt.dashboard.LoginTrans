// EmployeeProfileCard.kt
package mx.edu.uttt.dashboard.Screens.Components.UserProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

@Composable
fun EmployeeProfileCard(
    modifier: Modifier = Modifier,
    name: String,
    age: Int,
    weight: Int,
    healthStatus: String,
    medicalConditions: String,
    shift: String,
    area: String
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD0E8F2)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF31708E)
                    )
                }
                Spacer(Modifier.width(8.dp))
                Text(name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            InfoRow("Edad", "$age años")
            InfoRow("Peso", "$weight kg")
            InfoRow("Estado de salud", healthStatus)
            InfoRow("Condiciones médicas", medicalConditions)
            InfoRow("Turno", shift)
            InfoRow("Área", area)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(value)
    }
}

