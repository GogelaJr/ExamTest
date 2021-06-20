package com.example.examtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var recoveryButton: Button
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var editEmailText: EditText
    private lateinit var editPasswordText: EditText
    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        recoveryButton = findViewById(R.id.ForgotButton)
        loginButton = findViewById(R.id.LoginButton)
        registerButton = findViewById(R.id.RegisterButton)
        editEmailText = findViewById(R.id.LoginEmail)
        editPasswordText = findViewById(R.id.LoginPassword)

        mAuth = FirebaseAuth.getInstance()
//        if(mAuth.currentUser != null){
//            gotoLogin()
//
//        }

        this.clicks()
    }
    private fun clicks(){
        loginButton.setOnClickListener(){

            val email: String = editEmailText.text.toString()
            val password: String = editPasswordText.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        goToMain()
                    }else{
                        Toast.makeText(this, "Email or Password is invalid", Toast.LENGTH_SHORT).show()

                    }
                }
        }
        registerButton.setOnClickListener(){
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        recoveryButton.setOnClickListener(){
            startActivity(Intent(this, RecoveryActivity::class.java))
            finish()
        }

    }
    private fun gotoLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
    private fun goToMain(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}