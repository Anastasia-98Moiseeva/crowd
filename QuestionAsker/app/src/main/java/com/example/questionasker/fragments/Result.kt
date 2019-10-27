package com.example.questionasker.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.questionasker.BackgroundFetcher
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router
import okhttp3.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL



class Result : Fragment(){

    private lateinit var router : Router

    private val url = "http://35.164.210.60:8080/get_toloka"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.result, container, false)

        val txtResult = view.findViewById<TextView>(R.id.txt_result)

        var str = "0"
        while (str == "0"){
            str = loadTextFromServer(url, txtResult)
        }

        return view
    }

    private fun loadTextFromServer(url : String, textResult:TextView) : String {
        var str = ""

        val handler = Handler()
        handler.postDelayed(
            Runnable {

                val request = Request.Builder().url(url).build()

                val client = OkHttpClient()

                client.newCall(request).enqueue(object : Callback {

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call?, response: Response?) {
                        val body = response?.body()?.string()
                        activity?.runOnUiThread(Runnable {
                            textResult.setText(body + "$")
                        })
                    }

                    override fun onFailure(call: Call?, e: IOException?) {
                        println("Falhou")
                    }

                })
            }, 90000)

        return str
    }
}