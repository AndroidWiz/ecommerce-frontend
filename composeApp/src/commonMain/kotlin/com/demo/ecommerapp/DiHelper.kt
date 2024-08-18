package com.demo.ecommerapp

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApp: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        koinApp?.invoke(this)
        modules()
    }
}