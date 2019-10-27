package com.example.questionasker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router

val message = "mes"

class MainFragment : Fragment(){

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val roteMeButton = view.findViewById<Button>(R.id.btn_rote_me)
        onClick(roteMeButton, ::RoteMe)

        val roteOtherButton = view.findViewById<Button>(R.id.btn_rote_other)
        onClick(roteOtherButton, ::Waiting)

        val recommendationsButton = view.findViewById<Button>(R.id.btn_recomendations)
        onClick(recommendationsButton, ::Recommendations)

        return view
    }

     private fun onClick(btn : Button, fragmentNew: () -> Fragment) {
         btn.setOnClickListener{
             router.navigateTo(true, fragmentNew)
         }
     }
}