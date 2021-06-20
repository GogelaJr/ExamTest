package com.example.examtest

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.*

class UploadActivity : AppCompatActivity() {
    var selectedPhotoUri: Uri? = null
    private lateinit var imageView: ImageView
    private lateinit var locationEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var chooseButton: Button
    private lateinit var uploadButton: Button

    //    private val PICK_IMAGE_REQUEST = 71


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        imageView = findViewById(R.id.image_preview)
        locationEditText = findViewById(R.id.uploadName)
        descriptionEditText = findViewById(R.id.uploadDescription)
        chooseButton = findViewById(R.id.uploadChoose)
        uploadButton = findViewById(R.id.uploadUpload)

        chooseButton.setOnClickListener() { openFileChooser() }
        uploadButton.setOnClickListener() { uploadImage() }

    }


    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 0)
    }

    private fun uploadImage() {
        if(selectedPhotoUri ==null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/uploads/$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Upload","Successfully uploaded image: ${it.metadata?.path}")
            }
    }

    private fun addUploadRecordToDb(uri: String) {
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Log.d("UploadActivity", "Picture was selected")
            }
        selectedPhotoUri = data?.data
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
        val bitmapDrawable = BitmapDrawable(bitmap)

        }
    }
