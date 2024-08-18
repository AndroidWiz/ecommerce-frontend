package com.demo.ecommerapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform