package viach.apps.dicing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import viach.apps.dicing.ui.theme.DicingTheme
import viach.apps.dicing.ui.view.screen.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DicingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    window.statusBarColor = MaterialTheme.colors.background.toArgb()
                    window.navigationBarColor = MaterialTheme.colors.background.toArgb()
                    MainScreen()
                }
            }
        }
    }
}