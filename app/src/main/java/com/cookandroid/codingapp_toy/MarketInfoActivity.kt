package com.cookandroid.codingapp_toy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.cookandroid.codingapp_toy.Utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_market_info.*
import java.lang.reflect.TypeVariable

class MarketInfoActivity : AppCompatActivity() {
    private var auth:FirebaseAuth= FirebaseAuth.getInstance()
    private val db:FirebaseFirestore=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)
        lecture_text.text=intent.getStringExtra("title")
        FirebaseUtils.getUid()
        FirebaseUtils.db
        val lecture= hashMapOf(
            "lecture_title" to intent.getStringExtra("title")
        )
        zzim.setOnClickListener{
            header_zzim.text="하트뿅뿅찜되었습니다"
            header_zzim.setTextColor(Color.BLUE)
            FirebaseUtils.db
                .collection("zzim")
                .document(FirebaseUtils.getUid())
                .set(lecture)
                .addOnSuccessListener {
                    Toast.makeText(this,"성공",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"실패",Toast.LENGTH_LONG).show()
                }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area,ContentFragment())
            .commit()

        figure_1.setOnClickListener{
           figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,ContentFragment())
                .commit()
        }

        figure_2.setOnClickListener{

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,ReviewFragment())
                .commit()
        }

        figure_3.setOnClickListener{
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area,InfoFragment())
                .commit()
        }

    }
}