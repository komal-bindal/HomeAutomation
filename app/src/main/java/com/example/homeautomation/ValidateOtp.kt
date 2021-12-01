package com.example.homeautomation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class ValidateOtp : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_otp)

        auth = FirebaseAuth.getInstance()
        val storedVerificationId = intent.getStringExtra("storedVerificationId")


        findViewById<Button>(R.id.verifyOtpButton).setOnClickListener {
            val otp = findViewById<EditText>(R.id.otpCode1).text.trim()
                .toString() + findViewById<EditText>(R.id.otpCode2).text.trim()
                .toString() + findViewById<EditText>(R.id.otpCode3).text.trim()
                .toString() + findViewById<EditText>(R.id.otpCode4).text.trim()
                .toString() + findViewById<EditText>(R.id.otpCode5).text.trim()
                .toString() + findViewById<EditText>(R.id.otpCode6).text.trim()
                .toString()
            if (otp.isNotEmpty()) {
                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                    storedVerificationId.toString(), otp
                )
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(this, "Enter OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, ValidateOtp::class.java)
                    startActivity(intent)
                    finish()
                } else {

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}