package com.xperiencelabs.arapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.xperiencelabs.arapp.data.ModelData
import com.xperiencelabs.arapp.utils.LocationUtils

class ARViewModel : ViewModel() {
    private val models =
        listOf(
            ModelData("Facultad de Ingenier?a", -0.210143, -78.488552, "models/esfot.glb"),
            ModelData("Facultad de Medicina", -0.209694, -78.489096, "models/agro.glb"),
            ModelData("Facultad de Derecho", -0.211062, -78.489986, "models/ciencias.glb"),
            ModelData("Facultad de Arquitectura", -0.209428, -78.487355, "models/ciencias_administrativas.glb"),
            ModelData("Facultad de Ciencias Econ?micas", -0.21182746820550818, -78.49144985397021, "models/civil.glb"),
            ModelData("Facultad de Ciencias Sociales", -0.20941567651113266, -78.48953330600783, "models/electrica.glb"),
            ModelData("Facultad de Humanidades", -0.20941567651113266, -78.48953330600783, "models/electronica.glb"),
            ModelData("Facultad de Ciencias Exactas", -0.21097778432997027, -78.48924916033992, "models/geo.glb"),
            ModelData("Facultad de Veterinaria", -0.20946360122199537, -78.4898820819517, "models/mecanica.glb"),
            ModelData("Facultad de Agronom?a", -0.21097778432997027, -78.48924916033992, "models/petro.glb"),
            ModelData("Facultad de Bellas Artes", -0.20981111585155457, -78.48906179699483, "models/quimica.glb"),
            ModelData("Facultad de Psicolog?a", -0.21027703512547422, -78.48896165876667, "models/sistemas.glb"),
        )

    fun checkUserLocationAgainstModels(
        userLatitude: Double,
        userLongitude: Double,
    ): ModelData? {
        for (model in models) {
            val distance =
                LocationUtils.calculateDistance(
                    userLatitude,
                    userLongitude,
                    model.latitude,
                    model.longitude,
                )
            if (distance <= 50) { // Umbral de 50 metros
                return model
            }
        }
        return null
    }
}

