package mx.edu.uttt.dashboard.Screens.Dashboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.SearchBar

@Composable
fun EmployeesListScreen(
    modifier: Modifier = Modifier,
    employees: List<EmployeeProfileData>,
    onSearch: (String) -> Unit,
    onFilter: (String, String) -> Unit,
    onEmployeeSelected: (EmployeeProfileData) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            onSearch = onSearch,
            onFilterChange = onFilter
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(employees) { emp ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEmployeeSelected(emp) },
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(emp.photoUrl),
                            contentDescription = emp.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp)
                                .clip(RoundedCornerShape(28.dp))
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(emp.name, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Ver más...", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
data class EmployeeProfileData(
    val name: String,
    val age: Int,
    val weight: Int,
    val healthStatus: String,
    val medicalConditions: String,
    val shift: String,
    val area: String,
    val photoUrl: String
)

