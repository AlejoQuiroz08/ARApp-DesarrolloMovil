package com.xperiencelabs.arapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.xperiencelabs.arapp.R
import com.xperiencelabs.arapp.data.ModelData
import com.xperiencelabs.arapp.ui.viewmodel.ARViewModel
import com.xperiencelabs.arapp.utils.LocationUtils
import com.xperiencelabs.arapp.utils.SensorUtils
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class MainActivity : AppCompatActivity() {

    // Views
    private lateinit var sceneView: ArSceneView

    // Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // ViewModel
    private lateinit var arViewModel: ARViewModel

    // Sensors
    private lateinit var sensorUtils: SensorUtils

    // Permissions
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startLocationUpdates()
        } else {
            // Mostrar un mensaje al usuario indicando que el permiso es necesario
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        sceneView = findViewById(R.id.sceneView)

        // Inicializar servicios
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        arViewModel = ViewModelProvider(this).get(ARViewModel::class.java)
        sensorUtils = SensorUtils(this)

        // Verificar permisos de la cámara
        checkCameraPermission()
    }

    override fun onResume() {
        super.onResume()
        // Registrar el listener del sensor de orientación
        sensorUtils.registerSensorListener()
    }

    override fun onPause() {
        super.onPause()
        // Desregistrar el listener del sensor de orientación
        sensorUtils.unregisterSensorListener()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permiso de la cámara
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else {
            // Iniciar actualizaciones de ubicación si el permiso ya está otorgado
            startLocationUpdates()
        }
    }

    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permiso de ubicación si no está otorgado
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Obtener la última ubicación conocida
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val userLatitude = location.latitude
                    val userLongitude = location.longitude
                    checkUserLocationAgainstModels(userLatitude, userLongitude)
                }
            }
        }
    }

    private fun checkUserLocationAgainstModels(userLatitude: Double, userLongitude: Double) {
        val model = arViewModel.checkUserLocationAgainstModels(userLatitude, userLongitude)
        if (model != null) {
            showModel(model)
        }
    }

    private fun showModel(model: ModelData) {
        val modelNode = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = model.glbFileLocation,
                scaleToUnits = model.scaleToUnits,
                centerOrigin = model.centerOrigin
            ) {
                sceneView.planeRenderer.isVisible = true
            }
        }
        sceneView.addChild(modelNode)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}