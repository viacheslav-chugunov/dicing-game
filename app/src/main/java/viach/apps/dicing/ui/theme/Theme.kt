package viach.apps.dicing.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(

)

private val LightColorPalette = lightColors(
    primary = Brown500,
    primaryVariant = BrownDark500,
    onPrimary = Color.White,
    secondary = Brown500,
    secondaryVariant = BrownDark500,
    onSecondary = Color.White,
    background = AmberLight700a,
    onBackground = Color.Black,
    surface = Amber700a,
    onSurface = Color.Black
)

@Composable
fun DicingTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}