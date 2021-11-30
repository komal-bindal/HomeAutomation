package com.example.homeautomation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SignUpScreen : AppCompatActivity() {
    private lateinit var signin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        signin = findViewById(R.id.signinText)
        signin.setOnClickListener {
            startActivity(Intent(this, SigninScreen::class.java))
            finish()
        }
    }
}