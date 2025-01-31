package com.xperiencelabs.arapp.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorUtils(context: Context) : SensorEventListener {
    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var orientationSensor: Sensor? = null
    var azimuth: Float = 0f

    init {
        // Obtener el sensor de orientación
        orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
    }

    // Registrar el listener del sensor
    fun registerSensorListener() {
        orientationSensor?.let { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    // Desregistrar el listener del sensor
    fun unregisterSensorListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ORIENTATION) {
            azimuth = event.values[0] // Orientación en grados (0 = Norte, 90 = Este, etc.)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No es necesario implementar esto
    }
}