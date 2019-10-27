package com.example.questionasker.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.questionasker.FragmentActivity
import com.example.questionasker.R
import com.example.questionasker.Router
import com.squareup.picasso.Picasso
import java.net.URL

class Recommendations : Fragment(){

    private lateinit var router : Router

    private var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = Router(requireActivity() as FragmentActivity, R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.result, container, false)

        val uploadButton = view.findViewById<Button>(R.id.btn_upload_photo)
        uploadButton.setOnClickListener{
           // pickPhotoFromGallery()
        }

        return view
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val photo = view?.findViewById<ImageView>(R.id.img_uploaded)!!
        val plusImg = view?.findViewById<ImageButton>(R.id.img_btn_plus)!!

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                fileUri = data?.data
            }
            photo.setBackgroundColor(Color.TRANSPARENT)
            plusImg.setAlpha(0.0f)
            loadPhoto(context, fileUri, photo)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
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
    }*/

}