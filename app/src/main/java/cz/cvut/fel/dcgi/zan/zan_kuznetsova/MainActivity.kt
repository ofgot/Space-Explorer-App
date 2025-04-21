package cz.cvut.fel.dcgi.zan.zan_kuznetsova

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.di.appModule
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.navigation.AppRouter
import cz.cvut.fel.dcgi.zan.zan_kuznetsova.ui.theme.ZankuznetsovaTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        startKoin{
//            androidLogger()
//            androidContext(this@MainActivity)
//            modules(appModule)
//        }

        enableEdgeToEdge()

        setContent {
            ZankuznetsovaTheme {
                AppRouter()
            }
        }
    }


}
