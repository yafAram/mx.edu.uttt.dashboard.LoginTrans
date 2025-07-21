package mx.edu.uttt.dashboard.Screens.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.Dp
import mx.edu.uttt.dashboard.R

@Composable
fun LogoPanel(
    modifier: Modifier = Modifier,
    // Ajusta estos tamaños según tus SVG reales
    largeBlob: Dp = 120.dp,
    mediumBlob: Dp = 80.dp,
    smallBlob: Dp = 50.dp,
    logoScale: Float = 1.0f
) {
    Box(
        modifier = modifier
            .fillMaxHeight()            // Ocupa toda la altura disponible
            .aspectRatio(1.13f)          // Relación ancho:alto aproximada
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        // Esquina superior izquierda – blob grande
        Image(
            painter = painterResource(id = R.drawable.blob_orange),
            contentDescription = null,
            modifier = Modifier
                .size(largeBlob)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Fit
        )

        // Esquina superior derecha – blob mediano
        Image(
            painter = painterResource(id = R.drawable.blob_green),
            contentDescription = null,
            modifier = Modifier
                .size(mediumBlob)
                .align(Alignment.TopEnd),
            contentScale = ContentScale.Fit
        )

        // Fondo inferior izquierdo – blob mediano
        Image(
            painter = painterResource(id = R.drawable.blob_green),
            contentDescription = null,
            modifier = Modifier
                .size(mediumBlob)
                .align(Alignment.BottomStart),
            contentScale = ContentScale.Fit
        )

        // Fondo inferior izquierdo – blob pequeño (sobrepuesto)
        Image(
            painter = painterResource(id = R.drawable.blob_orange),
            contentDescription = null,
            modifier = Modifier
                .size(smallBlob)
                .align(Alignment.BottomStart)
                .offset(x = smallBlob, y = (-smallBlob / 2)),
            contentScale = ContentScale.Fit
        )

        // Logo central
        Image(
            painter = painterResource(id = R.drawable.ic_logintrans),
            contentDescription = "Logo de la empresa",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(logoScale),
            contentScale = ContentScale.Fit
        )
    }
}


