package com.xperiencelabs.arapp.data

import io.github.sceneview.math.Position

data class ModelData(
    val name: String,
    val latitude: Double,        // Latitud del modelo
    val longitude: Double,       // Longitud del modelo
    val glbFileLocation: String, // Ruta del archivo GLB
    val scaleToUnits: Float = 1f, // Escala del modelo
    val centerOrigin: Position = Position(0f, 0f, 0f) // Posición del modelo
)