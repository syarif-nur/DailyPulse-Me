package com.threedotz.dailypulse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform