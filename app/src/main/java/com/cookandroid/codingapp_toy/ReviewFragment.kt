package com.cookandroid.codingapp_toy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cookandroid.codingapp_toy.Fragment.MarketInfo.ReviewListAdapter
import com.cookandroid.codingapp_toy.Fragment.MarketInfo.Write2Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_review.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ReviewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var auth : FirebaseAuth
    private val db=FirebaseFirestore.getInstance()
    private val text_array=ArrayList<String>()
    private val nickname_array=ArrayList<String>()
    private val rating_array=ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        val view : View= inflater.inflate(R.layout.fragment_review, container, false)
        ///어댑터연결
        val review_adapter=ReviewListAdapter(requireContext(),nickname_array,text_array,rating_array)
        view.listview_review.adapter=review_adapter
        ///
        db.collection("reviews")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    rating_array.add(document.get("rating")as String)
                    text_array.add(document.get("text")as String)
                    nickname_array.add(document.get("writer")as String)
            }
                review_adapter.notifyDataSetChanged()
            }
            .addOnFailureListener{ exception ->
            }
        view.write_button.setOnClickListener {
            if(auth.currentUser==null){
                Toast.makeText(requireContext(),"회원가입 or 로그인",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(requireContext(), Write2Activity::class.java)
                startActivity(intent)

            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}