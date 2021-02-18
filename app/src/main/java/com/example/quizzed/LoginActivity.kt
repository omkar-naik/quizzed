package com.example.quizzed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            Login()
        }

        var btnSignUp1 = findViewById<TextView>(R.id.btnSignUp)
        btnSignUp1.setOnClickListener{
            val intent1 = Intent(this, SignupActivity::class.java)
            startActivity(intent1)
            finish()
        }
    }

    private fun Login() {
        var EmailAddress: EditText = findViewById<EditText>(R.id.etEmailAddress)
        var Password: EditText = findViewById<EditText>(R.id.etPassword)

        var email = EmailAddress.text.toString()
        var password = Password.text.toString()

        if(email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}