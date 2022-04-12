package com.cookandroid.codingapp_toy.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cookandroid.codingapp_toy.MainActivity
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        login_button.setOnClickListener {
            auth.signInWithEmailAndPassword(email_area.text.toString(),password_area.text.toString())
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){

                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"fail",Toast.LENGTH_LONG).show()
                    }

                }
        }

        join_button.setOnClickListener {
            val intent= Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
    }
}