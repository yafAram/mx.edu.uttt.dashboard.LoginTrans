// SearchBar.kt
package mx.edu.uttt.dashboard.Screens.Components.UserProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    filterOptions: List<String> = listOf("Turno", "Área", "Tipo alerta"),
    onFilterChange: (String, String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var expandedFilter by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = query,
            onValueChange = {
                query = it
                onSearch(it)
            },
            placeholder = { Text("") },
            modifier = Modifier
                .weight(1f)
                .height(40.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedContainerColor = Color(0xFFF0F0F0)
            )

        )
        filterOptions.forEach { option ->
            Box {
                Text(
                    option,
                    modifier = Modifier
                        .clickable { expandedFilter = option }
                        .background(Color(0xFFF0F0F0), RoundedCornerShape(20.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                DropdownMenu(
                    expanded = expandedFilter == option,
                    onDismissRequest = { expandedFilter = null }
                ) {
                    // TODO: sustituir con opciones dinámicas desde backend
                    listOf("Opción 1", "Opción 2").forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                onFilterChange(option, item)
                                expandedFilter = null
                            }
                        )
                    }
                }
            }
        }
    }
}