package com.example.examtest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.examtest.R

class RecoveryFragment: Fragment(R.layout.recovery_fragment) {
    private lateinit var editEmailText: EditText
    private lateinit var resetButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmailText = view.findViewById(R.id.RecoveryEmail)
        resetButton = view.findViewById(R.id.ResetButton)

        resetButton.setOnClickListener(){

        }

    }

}