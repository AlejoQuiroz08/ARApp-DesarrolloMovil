package com.xperiencelabs.arapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.node.VideoNode
import io.github.sceneview.math.Position
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var loginButton: MaterialButton
    private lateinit var modelNode: ArModelNode
    private lateinit var videoNode: VideoNode
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Lista de facultades con sus coordenadas y archivos GLB
    private val facultades = listOf(
        Facultad("Facultad de Ingeniería", -0.210143, -78.488552, "models/esfot.glb"),
        Facultad("Facultad de Medicina", -0.209694, -78.489096, "models/agro.glb"),
        Facultad("Facultad de Derecho", -0.211062, -78.489986, "models/ciencias.glb"),
        Facultad("Facultad de Arquitectura", -0.209428, -78.487355, "models/ciencias_administrativas.glb"),
        Facultad("Facultad de Ciencias Económicas", -0.21182746820550818, -78.49144985397021, "models/civil.glb"),
        Facultad("Facultad de Ciencias Sociales", -0.20941567651113266, -78.48953330600783, "models/electrica.glb"),
        Facultad("Facultad de Humanidades", -0.20941567651113266, -78.48953330600783, "models/electronica.glb"),
        Facultad("Facultad de Ciencias Exactas", -0.21097778432997027, -78.48924916033992, "models/geo.glb"),
        Facultad("Facultad de Veterinaria", -0.20946360122199537, -78.4898820819517, "models/mecanica.glb"),
        Facultad("Facultad de Agronomía", -0.21097778432997027, -78.48924916033992, "models/petro.glb"),
        Facultad("Facultad de Bellas Artes", -0.20981111585155457, -78.48906179699483, "models/quimica.glb"),
        Facultad("Facultad de Psicología", -0.21027703512547422, -78.48896165876667, "models/sistemas.glb")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneView = findViewById<ArSceneView>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.ad)

        placeButton = findViewById(R.id.place)
        loginButton = findViewById(R.id.loginButton)

        placeButton.setOnClickListener {
            placeModel()
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        videoNode = VideoNode(sceneView.engine, scaleToUnits = 0.7f, centerOrigin = Position(y = -4f), glbFileLocation = "models/plane.glb", player = mediaPlayer, onLoaded = { _, _ ->
            mediaPlayer.start()
        })

        modelNode = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            onAnchorChanged = {
                placeButton.isGone = it != null
            }
        }

        sceneView.addChild(modelNode)
        modelNode.addChild(videoNode)

        // Inicializa el cliente de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Solicita permisos de ubicación
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            obtenerUbicacion()
        }
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitud = location.latitude
                        val longitud = location.longitude
                        val facultadCercana = encontrarFacultadCercana(latitud, longitud)
                        cargarLogoFacultad(facultadCercana)
                    }
                }
        }
    }

    private fun encontrarFacultadCercana(latitud: Double, longitud: Double): Facultad? {
        var facultadCercana: Facultad? = null
        var distanciaMinima = Double.MAX_VALUE

        for (facultad in facultades) {
            val distancia = calcularDistancia(latitud, longitud, facultad.latitud, facultad.longitud)
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia
                facultadCercana = facultad
            }
        }

        return facultadCercana
    }

    private fun calcularDistancia(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val radioTierra = 6371 // Radio de la Tierra en kilómetros
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return radioTierra * c
    }

    private fun cargarLogoFacultad(facultad: Facultad?) {
        if (facultad != null) {
            modelNode.loadModelGlbAsync(
                glbFileLocation = facultad.glbFileLocation,
                scaleToUnits = 1f,
                centerOrigin = Position(-0.5f)
            ) {
                sceneView.planeRenderer.isVisible = true
                val materialInstance = it.materialInstances[0]
            }
        }
    }

    private fun placeModel() {
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                obtenerUbicacion()
            }
        }
    }

    // Clase de datos para representar una facultad
    data class Facultad(
        val nombre: String,
        val latitud: Double,
        val longitud: Double,
        val glbFileLocation: String // Ruta del archivo GLB del logo
    )
}