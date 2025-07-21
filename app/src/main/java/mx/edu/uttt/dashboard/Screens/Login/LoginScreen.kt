package mx.edu.uttt.dashboard.Screens.Login

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import mx.edu.uttt.dashboard.Screens.Components.*

@Composable
fun LoginScreen(onLogin: (String, String) -> Unit, modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (!isLandscape) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TitleText("Por favor gira tu dispositivo a horizontal")
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(66.dp)
        ) {
            LogoPanel(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleText("Inicia Sesión!")
                Spacer(Modifier.padding(top = 6.dp, bottom = 16.dp))
                InputField(
                    label = "Nombre usuario:",
                    value = username,
                    onValueChange = { username = it }
                )
                Spacer(Modifier.height(8.dp))
                InputField(
                    label = "Contraseña:",
                    value = password,
                    onValueChange = { password = it },
                    isPassword = true
                )
                Spacer(Modifier.height(16.dp))
                PrimaryButton(
                    text = "Entrar",
                    onClick = { onLogin(username, password) }
                )
            }
        }
    }
}