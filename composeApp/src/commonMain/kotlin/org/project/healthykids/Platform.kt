package org.project.healthykids

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform