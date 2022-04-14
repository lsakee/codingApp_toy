package com.cookandroid.codingapp_toy.auth

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.cookandroid.codingapp_toy.MainActivity
import com.cookandroid.codingapp_toy.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_my_comin.*
import java.io.ByteArrayOutputStream

class MyCominActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db =FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
      //  val db = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comin)
        auth = FirebaseAuth.getInstance()
        val docRef=db.collection("users").document(auth.currentUser?.uid.toString())
        docRef.get().addOnSuccessListener {documentSnapshot ->
            nickname_area.setText(documentSnapshot.get("nickname").toString())
        }
        logout_button.setOnClickListener {
            auth.signOut()
            val intent=Intent(this,MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        //이미지다운로드
        image_download.setOnClickListener {
            val ref = FirebaseStorage.getInstance().getReference("..write_button.png")
            ref.downloadUrl
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Glide.with(this)
                            .load(task.result)
                            .into(profile_img)
                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
                })
            //이미지업로드
            val mountainsRef = FirebaseStorage.getInstance().getReference().child("mountains.jpg")
            val bitmap = (imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener { taskSnapshot ->

                Toast.makeText(this, "업로드성공", Toast.LENGTH_LONG).show()
            }

        }



    }
}
/*
myRef.addValueEventListener(object : ValueEventListener {
    override fun onDataChange(dataSnapshot: DataSnapshot) {

        val value=dataSnapshot.getValue<String>()
        nickname_area.text="$value"

    }

    override fun onCancelled(error: DatabaseError) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException())
    }
})*/