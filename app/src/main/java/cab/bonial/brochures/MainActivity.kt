package cab.bonial.brochures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import cab.bonial.brochures.ui.navigation.Brochures
import cab.bonial.brochures.ui.screen.brochures.BrochuresScreen
import cab.bonial.brochures.ui.theme.BrochuresTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrochuresTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<Any>(Brochures) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        },
        entryProvider = { key ->
            when (key) {
                is Brochures -> NavEntry(key) {
                    BrochuresScreen()
                }

                else -> NavEntry(key) { /* Handle unknown routes */ }
            }
        }
    )
}