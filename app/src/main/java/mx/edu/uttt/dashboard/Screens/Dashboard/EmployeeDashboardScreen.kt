package mx.edu.uttt.dashboard.Screens.Dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import mx.edu.uttt.dashboard.R
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.AlertData
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.EmployeeProfileCard
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.EventHistoryCard
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.EventItem
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.PersonalIndicatorsCard
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.RecentAlertsCard
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.SearchBar
import mx.edu.uttt.dashboard.components.Sidebar
import mx.edu.uttt.dashboard.components.NavItem

@Composable
fun EmployeeDashboardScreen(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onFilter: (String, String) -> Unit,
    employees: List<EmployeeProfileData>,
    alerts: List<AlertData>,
    indicatorsTitles: List<String>,
    indicatorsValues: List<Float>,
    events: List<EventItem>,
    onNavigateBack: () -> Unit
) {
    val config = LocalConfiguration.current
    val isTablet = config.smallestScreenWidthDp >= 600

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(Modifier.fillMaxSize()) {
            // Menú lateral
            Sidebar(
                navItems = listOf(
                    NavItem(R.drawable.ic_chart, "General", false),
                    NavItem(R.drawable.ic_users, "Empleados", true),
                    NavItem(R.drawable.ic_settings, "Configuración", false)
                ),
                onItemSelected = { item ->
                    when (item.label) {
                        "General" -> onNavigateBack()
                        "Empleados" -> { /* ya estamos aquí */ }
                        "Configuración" -> { /* manejar config */ }
                    }
                }
            )

            // Contenido principal
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Barra de búsqueda con filtros
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    onSearch = onSearch,
                    onFilterChange = onFilter
                )

                if (isTablet) {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            EmployeeProfileCard(
                                name = employees.firstOrNull()?.name ?: "",
                                age = employees.firstOrNull()?.age ?: 0,
                                weight = employees.firstOrNull()?.weight ?: 0,
                                healthStatus = employees.firstOrNull()?.healthStatus ?: "",
                                medicalConditions = employees.firstOrNull()?.medicalConditions ?: "",
                                shift = employees.firstOrNull()?.shift ?: "",
                                area = employees.firstOrNull()?.area ?: "",
                                modifier = Modifier.fillMaxWidth()
                            )
                            RecentAlertsCard(
                                alerts = alerts,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Column(
                            Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            PersonalIndicatorsCard(
                                titles = indicatorsTitles,
                                values = indicatorsValues,
                                modifier = Modifier.fillMaxWidth()
                            )
                            EventHistoryCard(
                                events = events,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                } else {
                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        employees.firstOrNull()?.let { emp ->
                            EmployeeProfileCard(
                                name = emp.name,
                                age = emp.age,
                                weight = emp.weight,
                                healthStatus = emp.healthStatus,
                                medicalConditions = emp.medicalConditions,
                                shift = emp.shift,
                                area = emp.area
                            )
                        }
                        RecentAlertsCard(alerts = alerts)
                        PersonalIndicatorsCard(
                            titles = indicatorsTitles,
                            values = indicatorsValues
                        )
                        EventHistoryCard(events = events)
                    }
                }
            }
        }
    }
}

// Data models usados por la pantalla

data class EmployeeProfileData(
    val name: String,
    val age: Int,
    val weight: Int,
    val healthStatus: String,
    val medicalConditions: String,
    val shift: String,
    val area: String
)
