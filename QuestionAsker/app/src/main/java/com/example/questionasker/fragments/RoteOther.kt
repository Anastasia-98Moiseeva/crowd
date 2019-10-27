package com.example.questionasker.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.net.URL
import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi


class RoteOther : Fragment(){

    private var index = 1

    private lateinit var router : Router

    private val txtUrl = "http://35.164.210.60:8080/me_for_crowd_get_problem_text_question"
    private val imgLeftUrl = "http://35.164.210.60:8080/me_for_crowd_get_problem_first_photo"
    private val imgRightUrl = "http://35.164.210.60:8080/me_for_crowd_get_problem_second_photo"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.rote_other, container, false)

        val imgLeft = view.findViewById<ImageView>(R.id.img_left)
        loadPhotoFromServer(imgLeftUrl + index.toString(), imgLeft)

        val imgRight = view.findViewById<ImageView>(R.id.img_right)
        loadPhotoFromServer(imgRightUrl + index.toString(), imgRight)

        val txtProblem = view.findViewById<TextView>(R.id.txt_problem)
        val s = loadTextFromServer(txtUrl + index.toString())
        txtProblem.setText(s)

        val radioBtnLeft = view.findViewById<RadioButton>(R.id.radioButton_left)
        val radioBtnRight = view.findViewById<RadioButton>(R.id.radioButton_right)
        setRadioListener(radioBtnLeft, radioBtnRight)
        setRadioListener(radioBtnRight, radioBtnLeft)

        val nextBtn = view.findViewById<Button>(com.example.questionasker.R.id.btn_next)
        nextBtn.setOnClickListener{
            index++
            radioBtnLeft.setChecked(false)
            radioBtnRight.setChecked(false)
            loadPhotoFromServer(imgLeftUrl + (index % 3 + 1).toString(), imgLeft)
            loadPhotoFromServer(imgRightUrl + (index % 3 + 1).toString(), imgRight)
            val s0 = loadTextFromServer(txtUrl + (index % 3 + 1).toString())
            txtProblem.setText(s0)
        }
        return view
    }

    private fun setRadioListener(radioBtnFirst : RadioButton, radioBtnSec : RadioButton){
        radioBtnFirst.setOnClickListener{
            radioBtnSec.setChecked(false)
        }
    }

    private fun loadTextFromServer(url : String) : String{
        var str = ""
        while (str == ""){
            Thread {
                str = URL(url).readText()
            }.start()
        }
        return str
    }

    private fun loadPhotoFromServer(url : String, photo : ImageView){
        Picasso.with(context).invalidate(url)
        Picasso.with(context)
            .load(url)
            .into(photo, object : Callback {
                @SuppressLint("ShowToast")
                override fun onError() {
                    /* val toast = Toast.makeText(context,
                         resources.getString(R.string.errorLoadingPhoto), Toast.LENGTH_LONG)
                     toast.setGravity(Gravity.CENTER, 0, 0)
                     toast.show()*/
                }

                override fun onSuccess() {
                    /*setVisibility(progressBar, filterTextView,
                        amazingTextView, sharePhotoButton, photo)*/
                }
            })
    }
}