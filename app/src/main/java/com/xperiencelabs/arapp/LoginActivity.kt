package com.xperiencelabs.arapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asegúrate de tener este layout creado

        val loginButton = findViewById<Button>(R.id.login2)

        loginButton.setOnClickListener {
            val intent = Intent(this, FormLoginActivity::class.java)
            startActivity(intent)
        }
    }
}