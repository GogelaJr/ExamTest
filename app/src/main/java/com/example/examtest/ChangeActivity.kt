package com.example.examtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangeActivity : AppCompatActivity() {
    private lateinit var newpassword: EditText
    private lateinit var newpasswordrepeat: EditText
    private lateinit var confirmChange: Button
    private lateinit var cancelButton: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)

        newpassword = findViewById(R.id.changePassword)
        newpasswordrepeat = findViewById(R.id.changeRepeat)
        confirmChange = findViewById(R.id.changeConfirm)
        cancelButton = findViewById(R.id.changeCancel)
        mAuth = FirebaseAuth.getInstance()

        this.listeners()
    }
    private fun listeners(){
        confirmChange.setOnClickListener(){
            val password: String = newpassword.text.toString()
            val repeat: String = newpassword.text.toString()
            if(password.isEmpty() || repeat.isEmpty()){
                Toast.makeText(this, "All Fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password != repeat){
                Toast.makeText(this,"Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                Toast.makeText(this,"Passwords should be minimum 8 Symbols", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.currentUser?.updatePassword(password)?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Password changed Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
        }
    }
        cancelButton.setOnClickListener(){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}