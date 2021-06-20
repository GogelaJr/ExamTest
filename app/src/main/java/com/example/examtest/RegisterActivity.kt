package com.example.examtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatpasswordEditText: EditText
    private lateinit var registerfinish: Button
    private lateinit var backButton: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailEditText = findViewById(R.id.RegisterEmail)
        passwordEditText = findViewById(R.id.RegisterPassword)
        repeatpasswordEditText = findViewById(R.id.RepeatPassword)
        registerfinish = findViewById(R.id.EndRegisterButton)
        backButton = findViewById(R.id.registerBack)
        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")


        registerfinish.setOnClickListener(){

            val email: String = emailEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            val repeatpassword: String = repeatpasswordEditText.text.toString()

            if(email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()){
                Toast.makeText(this,"Fill all Fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password.length < 8){
                Toast.makeText(this, "Password must be minimum 8 Characters long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password != repeatpassword){
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show()
                        info(email)
                        db.child(mAuth?.currentUser?.uid!!).addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot){
                            val userInfo: UserInfo = snapshot.getValue(UserInfo::class.java) ?: return

                        }
                            override fun onCancelled(error: DatabaseError){

                            }

                        })
                        gotoLogin()
                    }else{
                        Toast.makeText(this, "Error occured! Please Try Again later", Toast.LENGTH_SHORT).show()
                        return@addOnCompleteListener
                    }
                }


        }
        backButton.setOnClickListener(){
            gotoLogin()
        }

    }

    private fun info(email: String) {
        val userInfo = UserInfo(email)
        db.child(mAuth.currentUser?.uid!!).setValue(userInfo)

    }

    private fun gotoLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}