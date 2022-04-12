package com.cookandroid.codingapp_toy.Fragment.MarketInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Write2Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write2)

        auth= FirebaseAuth.getInstance()
        val docRef=db.collection("users").document(auth.currentUser?.uid.toString())
    }
}