package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.examtest.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegisterFragment: Fragment(R.layout.register_fragment) {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerfinish: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEditText = view.findViewById(R.id.RegisterEmail)
        passwordEditText = view.findViewById(R.id.RegisterPassword)
        registerfinish = view.findViewById(R.id.EndRegisterButton)

        registerfinish.setOnClickListener(){

        }

    }
}