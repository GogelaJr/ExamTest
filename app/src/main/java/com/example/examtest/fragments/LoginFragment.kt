package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.examtest.R

class LoginFragment: Fragment(R.layout.login_fragment) {
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button
    private lateinit var editEmailText: EditText
    private lateinit var editPasswordText: EditText
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = Navigation.findNavController(view)

        loginButton = view.findViewById(R.id.LoginButton)
        registerButton = view.findViewById(R.id.RegisterButton)
        editEmailText = view.findViewById(R.id.LoginEmail)
        editPasswordText = view.findViewById(R.id.LoginPassword)

        registerButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            navController.navigate(action)
        }

    }
}