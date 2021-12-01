package com.example.homeautomation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OtpLogin : AppCompatActivity() {
    private lateinit var phoneNumberEditText: EditText
    private lateinit var getOtpButton: Button
    private lateinit var verificationOTP: String
    private lateinit var signupText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_otp_login)

        phoneNumberEditText = findViewById(R.id.phoneEditText)
        getOtpButton = findViewById(R.id.verifyOtpButton)
        signupText=findViewById(R.id.signupText)

        phoneNumberEditText.requestFocus()

        signupText.setOnClickListener {
            startActivity(Intent(this, SignUpScreen::class.java))
            finish()
        }

        getOtpButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    if (phoneNumberEditText.text.toString().trim().length<10) {
                        Toast.makeText(
                            applicationContext,
                            "Please enter valid phone number",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return
                    }
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + phoneNumberEditText.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        this@OtpLogin,
                        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            override fun onCodeSent(
                                verificationId: String,
                                forceResendingToken: PhoneAuthProvider.ForceResendingToken
                            ) {
                                super.onCodeSent(verificationId, forceResendingToken)
                                verificationOTP = verificationId
                                val phoneNumber = "+91 " + phoneNumberEditText.text.toString()
                                val intent =
                                    Intent(applicationContext,ValidateOtp::class.java)
                                intent.putExtra("PhoneNumber", phoneNumber.toString())
                                intent.putExtra("VerificationOTP", verificationOTP.toString())
                                startActivity(intent)
                                finish()
                            }

                            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                            }

                            override fun onVerificationFailed(e: FirebaseException) {
                                Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            }
        )


    }
}