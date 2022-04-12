package com.cookandroid.codingapp_toy.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cookandroid.codingapp_toy.MainActivity
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*

class JoinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        auth = Firebase.auth
        join_login_button.setOnClickListener {
            auth.createUserWithEmailAndPassword(join_email_area.text.toString(),join_password_area.text.toString())
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){

                        val intent= Intent(this, JoinInfoActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"fail", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}