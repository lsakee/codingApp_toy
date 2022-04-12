package com.cookandroid.codingapp_toy.Fragment.MarketInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.cookandroid.codingapp_toy.MainActivity
import com.cookandroid.codingapp_toy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_write2.*

class Write2Activity : AppCompatActivity() {
    private lateinit var rating_num : String
    private lateinit var auth: FirebaseAuth
    private lateinit var nickname: String
    private val db= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write2)

        auth= FirebaseAuth.getInstance()
        //rating
        rating_area.setOnRatingBarChangeListener{ratingBar,fl,b->
            rating_num=fl.toString()
        }
        //닉네임 받아오기
        val docRef=db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot->
            nickname=documentSnapshot.get("nickname") as String
        }
        //rating버튼눌르면 데베에 리뷰저장
        writing_button.setOnClickListener {
            val form = hashMapOf(
                "test" to text_input_area.text.toString(),
                "writer" to nickname,
                "rating" to rating_num
            )
            db.collection("reviews")
                .add(form)
                .addOnSuccessListener {
                    Toast.makeText(this,"성공",Toast.LENGTH_LONG).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"실패",Toast.LENGTH_LONG).show()
                }
        }
    }
}