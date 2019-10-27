package com.example.questionasker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onResume() {
        super.onResume()
        timer = object : CountDownTimer(waitTime, 1000) {
            override fun onTick(milSeconds: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@MainActivity, FragmentActivity::class.java)
                startActivity(intent)
                finish()
            }

        }.start()
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        waitTime = savedInstanceState.getLong(timeSave)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(timeSave, waitTime)
    }


    private var waitTime: Long = 2000
    private val timeSave: String = ""
    private lateinit var timer: CountDownTimer
}
