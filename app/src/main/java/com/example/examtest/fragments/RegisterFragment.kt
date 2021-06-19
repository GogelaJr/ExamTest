package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.examtest.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment: Fragment(R.layout.register_fragment) {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatpasswordEditText: EditText
    private lateinit var registerfinish: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var navController:  NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEditText = view.findViewById(R.id.RegisterEmail)
        passwordEditText = view.findViewById(R.id.RegisterPassword)
        repeatpasswordEditText = view.findViewById(R.id.RepeatPassword)
        registerfinish = view.findViewById(R.id.EndRegisterButton)
        mAuth = FirebaseAuth.getInstance()




        registerfinish.setOnClickListener(){

            val activity = getActivity()
            val email: String = emailEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            val repeatpassword: String = repeatpasswordEditText.text.toString()

            if(email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()){
                Toast.makeText(activity,"Fill all Fields!", Toast.LENGTH_SHORT).show()
            }
            if(password.length < 8){
                Toast.makeText(activity, "Password must be minimum 8 Characters long", Toast.LENGTH_SHORT).show()
            }
            if(password != repeatpassword){
                Toast.makeText(activity, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            }
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val regtolog: NavDirections = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                        navController.navigate(regtolog)
                    }else{
                        Toast.makeText(activity, "Error occured!", Toast.LENGTH_SHORT).show()
                    }
                }


        }

    }
}