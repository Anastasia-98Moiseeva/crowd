package com.example.questionasker.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.questionasker.BackgroundFetcher
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RoteMe : Fragment(){

    var index = 0

    private lateinit var router : Router

    private val url1 = "http://35.164.210.60:8080/crowd_for_me_ph1"
    private val url2 = "http://35.164.210.60:8080/crowd_for_me_ph2"

    private var fileUri1: Uri? = null
    private var fileUri2: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.rote_me, container, false)

        val img_left = view.findViewById<ImageView>(R.id.img_left)
        val img_right = view.findViewById<ImageView>(R.id.img_right)

        val plusButton = view.findViewById<ImageButton>(R.id.img_btn_plus)
        plusButton.setOnClickListener{
            pickPhotoFromGallery()
            index++
        }

        val roteButton = view.findViewById<Button>(R.id.rote)
        roteButton.setOnClickListener {
            val f1 = fileUri1
            val f2 = fileUri2
            Thread(BackgroundFetcher(activity, url1, fileUri1!!)).start()

            val handler = Handler()
            handler.postDelayed(
                Runnable {
                    Thread(BackgroundFetcher(activity, url2, fileUri2!!)).start()

                }, 10000)

            router.navigateTo(true, ::Timer)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(index % 2 == 0){
            val photo1 = view?.findViewById<ImageView>(R.id.img_left)!!
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == 1) {
                    fileUri1 = data?.data
                }
                loadPhoto(context, fileUri1, photo1)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        } else {

            var photo2 = view?.findViewById<ImageView>(R.id.img_right)!!
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == 1) {
                    fileUri2 = data?.data
                }
                loadPhoto(context, fileUri2, photo2)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    fun loadPhoto(context: Context?, fileUri : Uri?, photo : ImageView){
        Picasso
            .with(context)
            .load(fileUri)
            .into(photo)
    }

    private fun pickPhotoFromGallery() {
        val pickImageIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(pickImageIntent, 1)
    }

    private fun onClick(btn : Button, fragmentNew: () -> Fragment) {
        btn.setOnClickListener{
            router.navigateTo(true, fragmentNew)
        }
    }

}