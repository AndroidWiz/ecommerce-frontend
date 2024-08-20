package com.demo.ecommerapp

import com.demo.ecommerapp.data.di.dataModule
import com.demo.ecommerapp.domain.di.domainModule
import com.demo.ecommerapp.ui.di.uiModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApp: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        koinApp?.invoke(this)
        modules(dataModule, domainModule, uiModule)
    }
}