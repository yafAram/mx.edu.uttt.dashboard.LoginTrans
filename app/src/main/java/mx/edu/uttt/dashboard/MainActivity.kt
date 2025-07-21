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
import mx.edu.uttt.dashboard.Screens.Login.LoginScreen
import mx.edu.uttt.dashboard.Screens.Dashboard.DashboardScreen
import mx.edu.uttt.dashboard.ui.theme.DashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardTheme {
                // 1. Crear el controlador de navegación
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 2. Configurar el NavHost con las rutas
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Pantalla de Login
                        composable("login") {
                            LoginScreen(
                                onLogin = { username, password ->
                                    // Lógica de autenticación (simulada)
                                    if (username.isNotBlank() && password.isNotBlank()) {
                                        // 3. Navegar al dashboard
                                        navController.navigate("dashboard") {
                                            // Limpiar el back stack para que no pueda volver al login
                                            popUpTo("login") { inclusive = true }
                                        }
                                    } else {
                                        println("Usuario: $username, Pass: $password")
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // Pantalla de Dashboard
                        composable("dashboard") {
                            DashboardScreen(modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}
