package com.example.homeautomation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SigninScreen : AppCompatActivity() {
    private lateinit var signup: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin_screen)
        signup = findViewById(R.id.signupText)
        signup.setOnClickListener {
            startActivity(Intent(this, SignUpScreen::class.java))
            finish()
        }
    }
}