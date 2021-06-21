package com.example.examtest.fragments

import android.content.Intent
import android.graphics.Picture
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.examtest.ChangeActivity
import com.example.examtest.LoginActivity
import com.example.examtest.R
import com.example.examtest.UploadActivity
import com.google.android.gms.tasks.Continuation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadFragment:Fragment(R.layout.upload_fragment) {
    private lateinit var imageView:ImageView
    private lateinit var picturename:EditText
    private lateinit var description:EditText
    private lateinit var choose:Button
    private lateinit var upload:Button
    private lateinit var change:Button
    private lateinit var logout:Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var Picturebase: DatabaseReference
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private val PICK_IMAGE_REQUEST = 71


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        Picturebase = FirebaseDatabase.getInstance().getReference("Images")

//        imageView = view.findViewById(R.id.imageView)
        upload = view.findViewById(R.id.uploadButton)
        change = view.findViewById(R.id.resetPassword)
        logout = view.findViewById(R.id.logoutButton)

        this.listeners()
    }
    private fun listeners(){
        upload.setOnClickListener(){
            val activity = getActivity()
            startActivity(Intent(activity,UploadActivity::class.java))
            }
        change.setOnClickListener(){
            var activity = getActivity()
            startActivity(Intent(activity, ChangeActivity::class.java))
        }
        logout.setOnClickListener(){
            startActivity(Intent(activity, LoginActivity::class.java))
            mAuth.signOut()
        }
    }
}