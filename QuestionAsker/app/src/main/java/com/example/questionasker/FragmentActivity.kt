package com.example.questionasker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.questionasker.fragments.MainFragment


class FragmentActivity : AppCompatActivity() {

    lateinit var router  : Router

    var coinNumber = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity)

        router = Router(this, R.id.fragment_container)

        if (savedInstanceState == null) router.navigateTo(false, ::MainFragment)
    }

    override fun onBackPressed() {
        if (!router.navigateBack()) {
            super.onBackPressed()
        }
    }
}