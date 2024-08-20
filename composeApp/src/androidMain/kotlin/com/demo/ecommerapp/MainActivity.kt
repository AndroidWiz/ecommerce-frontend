package com.demo.ecommerapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

class BaseApplication() : Application() {

    override fun onCreate() {
        super.onCreate()

        // init koin
        initKoin {
            it.modules(
                module {
                    single { this@BaseApplication }
                    Napier.base(DebugAntilog())
                }
            )
        }
    }
}