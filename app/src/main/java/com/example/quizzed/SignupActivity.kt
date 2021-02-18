package com.example.quizzed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
//import kotlinx.android.synthetic.main.activity_signup.btnSignUp


class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()

        var btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            signUpUser()
        }

        var btnLogin1 = findViewById<TextView>(R.id.btnLogin)
        btnLogin1.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser() {

        var EmailAddress: EditText = findViewById(R.id.etEmailAddress)
        var Password: EditText = findViewById(R.id.etPassword)
        var ConfirmPassword: EditText = findViewById(R.id.etConfirmPassword)

        var email = EmailAddress.text.toString()
        var password = Password.text.toString()
        var confirmPassword = ConfirmPassword.text.toString()

        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if(it.isSuccessful) {
                Toast.makeText(this, "SignUp successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Error creating user.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
