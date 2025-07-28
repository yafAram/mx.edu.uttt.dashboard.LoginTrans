package mx.edu.uttt.dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.AlertData
import mx.edu.uttt.dashboard.Screens.Components.UserProfile.EventItem
import mx.edu.uttt.dashboard.Screens.Dashboard.DashboardScreen
import mx.edu.uttt.dashboard.Screens.Dashboard.EmployeeDashboardScreen
import mx.edu.uttt.dashboard.Screens.Dashboard.EmployeeProfileData
import mx.edu.uttt.dashboard.Screens.Dashboard.EmployeesListScreen
import mx.edu.uttt.dashboard.Screens.Login.LoginScreen
import mx.edu.uttt.dashboard.ui.theme.DashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Pantalla de Login
                        composable("login") {
                            LoginScreen(
                                onLogin = { username, password ->
                                    if (username.isNotBlank() && password.isNotBlank()) {
                                        navController.navigate("dashboard") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Dashboard General
                        composable("dashboard") {
                            DashboardScreen(
                                modifier = Modifier.fillMaxSize(),
                                onNavigateToEmployees = {
                                    navController.navigate("employees")
                                }
                            )
                        }

                       // Dashboard de Empleados
//                        composable("employees") {
//                            EmployeeDashboardScreen(
//                                modifier = Modifier.fillMaxSize(),
//                                onSearch = { /* TODO: implementar búsqueda */ },
//                                onFilter = { field, value -> /* TODO: implementar filtro */ },
//                                employees = listOf(
//                                    EmployeeProfileData(
//                                        name              = "Juan Pérez",
//                                        age               = 30,
//                                        weight            = 75,
//                                        healthStatus      = "Verde (sin alertas)",
//                                        medicalConditions = "Ninguna",
//                                        shift             = "Matutino",
//                                        area              = "Logística"
//                                    )
//                                ),
//                                alerts = listOf(
//                                    AlertData(
//                                        date    = "01 Jul 2025",
//                                        message = "Alerta de temperatura alta"
//                                    )
//                                ),
//                                indicatorsTitles = listOf("Fatiga", "Pausas"),
//                                indicatorsValues = listOf(70f, 40f),
//                                events = listOf(
//                                    EventItem(
//                                        datetime = "2025-07-23 08:00",
//                                        type     = "Temperatura",
//                                        value    = "38°C",
//                                        location = "Zona A",
//                                        action   = "Registrado"
//                                    )
//                                ),
//                                onNavigateBack = {
//                                    navController.navigate("dashboard") {
//                                        popUpTo("dashboard") { inclusive = true }
//                                    }
//                                }
//                            )
//                        }

                        // Ruta de lista
                        val empleadosLista = listOf(
                            EmployeeProfileData(
                                name              = "Juan Pérez",
                                age               = 30,
                                weight            = 75,
                                healthStatus      = "Verde (sin alertas)",
                                medicalConditions = "Ninguna",
                                shift             = "Matutino",
                                area              = "Logística",
                               // photoUrl          = "https://ejemplo.com/avatar1.jpg"
                            ),
                            // ... otros empleados
                        )
                        composable("employees_list") {
                            EmployeesListScreen(
                                employees = empleadosLista,
                                onSearch = { /* ... */ },
                                onFilter = { _, _ -> /* ... */ },
                                onEmployeeSelected = { selectedEmp ->
                                    // ...
                                }
                            )
                        }

                    }
                }
            }
        }
    }
}
