// DashboardScreen.kt
package mx.edu.uttt.dashboard.Screens.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import mx.edu.uttt.dashboard.R
import mx.edu.uttt.dashboard.components.*

@Composable
fun DashboardScreen(modifier: Modifier = Modifier, onNavigateToEmployees: () -> Unit) {
    val config = LocalConfiguration.current
    val isTablet = config.smallestScreenWidthDp >= 600

    // Datos simulados
    val dashboardData = DashboardData.createSampleData()

    Surface(modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Row(Modifier.fillMaxSize()) {
            Sidebar(
                navItems = listOf(
                    NavItem(R.drawable.ic_chart, "General", true),
                    NavItem(R.drawable.ic_users, "Empleados", false),
                    NavItem(R.drawable.ic_settings, "Configuración", false)
                ),
                onItemSelected = { item ->
                    when (item.label) {
                        "Empleados" -> onNavigateToEmployees()
                        else        -> { /* aquí puedes manejar otras rutas */ }
                    }
                }
            )

            // Contenido principal
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TopBar("Dashboard General", "Andrew", {})

                if (isTablet) {
                    // Layout para tablet
                    TabletDashboardLayout(dashboardData)
                } else {
                    // Layout para móvil
                    MobileDashboardLayout(dashboardData)
                }
            }
        }
    }
}

@Composable
private fun TabletDashboardLayout(data: DashboardData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Primera fila: Mapa + Índices
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            VerticalCardContainer (modifier = Modifier.weight(6f)){
                MapCard(
                    title = "Mapa de Empleados activos",
                    stateName = "Hidalgo",
                    svgRes = R.drawable.blob_orange,
                    dots = data.dots,
                    modifier = Modifier.fillMaxWidth()
                )


                AlertTableCard(
                    title = "Resumen de alertas activas",
                    alerts = data.alerts,
                    modifier = Modifier.fillMaxWidth()
                )

            }

            VerticalCardContainer(modifier = Modifier.weight(3f)) {

                ParentIndicesCard(
                    fatigueLabels = data.fatigueLabels,
                    fatigueValues = data.fatigueValues,
                    pauseLabels = data.pauseLabels,
                    pauseValues = data.pauseValues,
                    modifier = Modifier.fillMaxWidth()
                )

                    // Estadísticas accidentales
                    ProgressStatCard(
                        title = "Estadísticas accidentes",
                        stats = data.accidentStats,
                        modifier = Modifier.fillMaxWidth()
                    )

            }

        }


    }
}

@Composable
private fun MobileDashboardLayout(data: DashboardData) {
    Column(
        Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MapCard(
            title = "Mapa de Empleados activos",
            stateName = "Estado Actual",
            svgRes = R.drawable.blob_orange,
            dots = data.dots
        )
        ParentIndicesCard(
            fatigueLabels = data.fatigueLabels,
            fatigueValues = data.fatigueValues,
            pauseLabels = data.pauseLabels,
            pauseValues = data.pauseValues
        )
        AlertTableCard("Resumen de alertas activas", data.alerts)
        ProgressStatCard("Estadísticas accidentes", data.accidentStats)
        ContactAvatars(data.contacts)
        PromotionalCard()
    }
}

@Composable
fun PromotionalCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(0xFFFF8A65), shape = MaterialTheme.shapes.medium)
    ) {
        // Contenido promocional aquí
    }
}

data class DashboardData(
    val fatigueLabels: List<String>,
    val fatigueValues: List<Float>,
    val pauseLabels: List<String>,
    val pauseValues: List<Float>,
    val accidentStats: List<StatItem>,
    val dots: List<MapDot>,
    val alerts: List<AlertItem>,
    val contacts: List<Int>
) {
    companion object {
        fun createSampleData(): DashboardData {
            return DashboardData(
                fatigueLabels = listOf("Día", "Noche", "Tarde"),
                fatigueValues = listOf(72f, 90f, 60f),
                pauseLabels = listOf("Completadas", "Sugeridas"),
                pauseValues = listOf(73f, 70f),
                accidentStats = listOf(
                    StatItem("Somnolencia", 52f, Color(0xFFFFAB91)),
                    StatItem("FC alta", 21f, Color(0xFFC8E6C9)),
                    StatItem("Taquicardia", 74f, Color(0xFFBBDEFB))
                ),
                dots = listOf(
                    MapDot(0.3f, 0.4f, Color(0xFF9CCC65)),
                    MapDot(0.5f, 0.2f, Color(0xFFFFB74D)),
                    MapDot(0.7f, 0.6f, Color(0xFFE57373)),
                    MapDot(0.4f, 0.8f, Color(0xFFD32F2F))
                ),
                alerts = listOf(
                    AlertItem("Jorge", "Atendido", "Somnolencia", "13 Dec 2020", "Leve"),
                    AlertItem("Mario", "Atendido", "Taquicardia", "14 Dec 2020", "Media"),
                    AlertItem("Pedro", "Atendido", "FC alta", "07 Dec 2020", "Alta"),
                    AlertItem("Jonathan", "Atendido", "Somnolencia", "06 Dec 2020", "Grave"),
                    AlertItem("Marlin", "Atendido", "Somnolencia", "31 Nov 2020", "Leve")
                ),
                contacts = listOf(
                    R.drawable.avatar1, R.drawable.avatar2,
                    R.drawable.avatar3, R.drawable.avatar4, R.drawable.avatar1
                )
            )
        }
    }
}