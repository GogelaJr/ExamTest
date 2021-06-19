package com.example.examtest.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.examtest.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment: Fragment(R.layout.login_fragment) {
    private lateinit var recoveryButton: Button
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var editEmailText: EditText
    private lateinit var editPasswordText: EditText
    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = Navigation.findNavController(view)
        recoveryButton = view.findViewById(R.id.ForgotButton)
        loginButton = view.findViewById(R.id.LoginButton)
        registerButton = view.findViewById(R.id.RegisterButton)
        editEmailText = view.findViewById(R.id.LoginEmail)
        editPasswordText = view.findViewById(R.id.LoginPassword)

        mAuth = FirebaseAuth.getInstance()
        if(mAuth.currentUser != null){

        }

        this.clicks()




    }
    private fun clicks(){
        loginButton.setOnClickListener(){
            val activity = getActivity()
            val email: String = editEmailText.text.toString()
            val password: String = editPasswordText.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Please enter your Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val logtohome = LoginFragmentDirections.actionLoginFragmentToHomepageFragment()
                        navController.navigate(logtohome)
                    }else{
                        Toast.makeText(activity, "Email or Password is invalid", Toast.LENGTH_SHORT).show()
                    }
                }
        }


        registerButton.setOnClickListener{
            val logtoreg = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(logtoreg)
        }
        recoveryButton.setOnClickListener(){
            val logtorec = LoginFragmentDirections.actionLoginFragmentToRecoveryFragment()
            navController.navigate(logtorec)
        }
    }


}