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

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_login_button.setOnClickListener {
            val nickname=findViewById<EditText>(R.id.join_info_email_area)?.text.toString()
            val database = Firebase.database
            val myRef = database.getReference("message").child(Firebase.auth.currentUser!!.uid)

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
}


//
//myRef.addValueEventListener(object : ValueEventListener {
//    override fun onDataChange(dataSnapshot: DataSnapshot) {
//        // This method is called once with the initial value and again
//        // whenever data at this location is updated.
//        val value = dataSnapshot.getValue<String>()
//        Log.d(TAG, "Value is: $value")
//    }
//
//    override fun onCancelled(error: DatabaseError) {
//        // Failed to read value
//        Log.w(TAG, "Failed to read value.", error.toException())
//    }
//})