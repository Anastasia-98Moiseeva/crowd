package com.example.questionasker.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router

class Timer : Fragment(){

    private lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.timer, container, false)

        val timerView = view.findViewById<TextView>(R.id.txt_time)
        val timer = object: CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerView.setText((millisUntilFinished/1000).toString())
            }

            override fun onFinish() {
                router.navigateTo(true, ::Result, changeStack = 2)
            }
        }
        timer.start()

        return view
    }
}