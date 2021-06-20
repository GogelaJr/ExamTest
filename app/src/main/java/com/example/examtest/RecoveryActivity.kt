package com.example.examtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RecoveryActivity : AppCompatActivity() {
    private lateinit var editEmailText: EditText
    private lateinit var resetButton: Button
    private lateinit var backButton: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        editEmailText = findViewById(R.id.RecoveryEmail)
        resetButton = findViewById(R.id.ResetButton)
        backButton = findViewById(R.id.recoveryBack)
        mAuth = FirebaseAuth.getInstance()

        resetButton.setOnClickListener(){
            val email: String = editEmailText.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Reset Password Sent to Email", Toast.LENGTH_SHORT).show()
                        gotoLogin()
                    }else{
                        Toast.makeText(this,"Please enter a valid Email", Toast.LENGTH_SHORT).show()

                    }
                }

        }
        backButton.setOnClickListener(){
            gotoLogin()
        }

    }
    private fun gotoLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

}
}