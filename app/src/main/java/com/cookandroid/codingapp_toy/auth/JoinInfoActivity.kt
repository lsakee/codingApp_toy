package com.cookandroid.codingapp_toy.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.cookandroid.codingapp_toy.MainActivity
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join_info.*

class JoinInfoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_login_button.setOnClickListener {
            val user= hashMapOf(
                "nickname" to join_info_email_area.text.toString()
            )
            db.collection("users")
                .document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener { Log.e("JoinInfoActivity","성공")
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)}
                .addOnFailureListener { Log.e("JoinInfoActivity","실패") }
        }
    }
}

/*class JoinInfoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_login_button.setOnClickListener {
            val nickname=findViewById<EditText>(R.id.join_info_email_area)?.text.toString()
            val db = Firebase.database
            val myRef = db.getReference("message").child(Firebase.auth.currentUser!!.uid)

            myRef
                .push()
                .setValue(nickname)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){

                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"fail", Toast.LENGTH_LONG).show()
                    }
                }

    }
}
}*/