package com.xperiencelabs.arapp.utils

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object LocationUtils {
    fun calculateDistance(
        userLatitude: Double,
        userLongitude: Double,
        modelLatitude: Double,
        modelLongitude: Double
    ): Double {
        val earthRadius = 6371000 // Radio de la Tierra en metros

        val dLat = Math.toRadians(modelLatitude - userLatitude)
        val dLon = Math.toRadians(modelLongitude - userLongitude)

        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(userLatitude)) * cos(Math.toRadians(modelLatitude)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return earthRadius * c // Distancia en metros
    }
}