package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.examtest.R
import com.google.firebase.auth.FirebaseAuth

class RecoveryFragment: Fragment(R.layout.recovery_fragment) {
    private lateinit var editEmailText: EditText
    private lateinit var resetButton: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmailText = view.findViewById(R.id.RecoveryEmail)
        resetButton = view.findViewById(R.id.ResetButton)
        mAuth = FirebaseAuth.getInstance()

        resetButton.setOnClickListener(){
            val email: String = editEmailText.text.toString()
            val activity = getActivity()

            if(email.isEmpty()){
                Toast.makeText(activity, "Please enter your Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(activity,"Reset Password Sent to Email", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(activity,"Please enter a valid Email", Toast.LENGTH_SHORT).show()

                    }
                }

        }

    }

}