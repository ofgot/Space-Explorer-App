package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.AppRouter
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.screens.LaunchesScreen
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZankuznetsovaTheme {
                AppRouter()
            }
        }
    }
}
