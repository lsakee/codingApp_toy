package com.cookandroid.codingapp_toy.auth

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_my_comin.*

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