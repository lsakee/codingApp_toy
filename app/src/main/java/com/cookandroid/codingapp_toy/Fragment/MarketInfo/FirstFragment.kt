package com.cookandroid.codingapp_toy.Fragment.MarketInfo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.codingapp_toy.MarketInfoActivity
import com.cookandroid.codingapp_toy.R
import kotlinx.android.synthetic.main.fragment_first.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_first, container, false)

        val list_array= arrayListOf<ContentsListModel>(
            ContentsListModel(R.drawable.list1,"lang1",1,"d"),
            ContentsListModel(R.drawable.list2,"lang2",1,"d"),
            ContentsListModel(R.drawable.list3,"lang3",1,"d"),
            ContentsListModel(R.drawable.list4,"lang4",1,"d"),
            ContentsListModel(R.drawable.list5,"lang5",1,"d"),
            ContentsListModel(R.drawable.list6,"lang6",1,"d"),
            ContentsListModel(R.drawable.list7,"lang7",1,"d"),
            ContentsListModel(R.drawable.list8,"lang8",1,"d"),
            ContentsListModel(R.drawable.list9,"lang9",1,"d")
        )
        val list_adapter= FirstFragAdapter(requireContext(),list_array)
        view.listview_first_fragment.adapter=list_adapter

        view.listview_first_fragment.setOnItemClickListener{ adapterView,view,i,l->
            val intent= Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title",list_array.get(i).title)
            startActivity(intent)
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
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}