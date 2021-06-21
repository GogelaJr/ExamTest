package com.example.examtest

import android.app.Activity
import android.content.ContentValues.TAG
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.IOException
import java.util.*

class UploadActivity : AppCompatActivity() {
    var selectedPhotoUri: Uri? = null
    private lateinit var imageView: ImageView
    private lateinit var locationEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var chooseButton: Button
    private lateinit var uploadButton: Button
    private lateinit var db: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        db = FirebaseDatabase.getInstance().getReference("LocationInfo")
        mAuth = FirebaseAuth.getInstance()

        imageView = findViewById(R.id.image_preview)
        locationEditText = findViewById(R.id.uploadName)
        descriptionEditText = findViewById(R.id.uploadDescription)
        chooseButton = findViewById(R.id.uploadChoose)
        uploadButton = findViewById(R.id.uploadUpload)

        chooseButton.setOnClickListener() { openFileChooser()
        uploadImage()}
        uploadButton.setOnClickListener() {
            uploadInfo()
        }

    }

    private fun Imageinfo(locationname: String, description:  String) {
        val image = ImageInfo(locationname, description)
        db.child(mAuth.currentUser?.uid!!).setValue(image)

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
        val ref = FirebaseStorage.getInstance().getReference("uploads/$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("Upload","Successfully uploaded image: ${it.metadata?.path}")
                ref.downloadUrl.addOnCompleteListener {
                    it.toString()
                    Log.d("Upload","File Location  $it")
                    Toast.makeText(this,"Image Uploaded Successfuly", Toast.LENGTH_SHORT).show()

                }
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
        Picasso.get().load(selectedPhotoUri).into(imageView)
    }
    private fun uploadInfo(){
        val locationname = locationEditText.text.toString()
        val description = descriptionEditText.text.toString()
        Imageinfo(locationname, description)
        db.child(mAuth?.currentUser?.email!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                val imageInfo: ImageInfo = snapshot.getValue(ImageInfo::class.java) ?: return


            }
            override fun onCancelled(error: DatabaseError){

            }

        })
        // collections.max()


    }
}
