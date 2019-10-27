package com.example.questionasker.fragments

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import android.os.CountDownTimer
import android.widget.ImageView
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router


class Waiting : Fragment(){

    private lateinit var router : Router

    private lateinit var timer: CountDownTimer
    private var waitTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.waiting, container, false)

        //val progress = view.findViewById<com.github.lzyzsd.circleprogress.DonutProgress>(R.id.donut_progress)
        /*val set = AnimatorInflater.loadAnimator(context, R.animator.progress_anim2) as AnimatorSet
        set.setTarget(progress)
        set.start()*/

        timer = object : CountDownTimer(waitTime, 1000) {
            override fun onTick(milSeconds: Long) {
            }

            override fun onFinish() {
                router.navigateTo(true, ::RoteOther, changeStack = 2)
            }

        }.start()

        return view
    }

}