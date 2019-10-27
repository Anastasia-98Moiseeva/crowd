package com.example.questionasker

import android.net.Uri
import android.os.Environment
import androidx.fragment.app.FragmentActivity
import okhttp3.*
import java.io.File
import java.io.InputStream

class BackgroundFetcher(private val activity: FragmentActivity?,
                        private val url: String,
                        private val fileUri: Uri? = null) : Runnable{

    override fun run() {
        activity?.let {
            val translation = fetchTranslation(genFile(), url)
        }
    }

    fun InputStream.toFile(file: File) {
        file.outputStream().use { this.copyTo(it) }
    }

    fun genFile() : File {
        val fileInputStream : InputStream = activity?.contentResolver!!.openInputStream(fileUri)
        val path = Environment.getExternalStorageDirectory()
        val file : File = File(path, "/" + "newImg.png")
        fileInputStream.toFile(file)
        return file
    }

    fun fetchTranslation(file : File, url: String) : String {

        try {
            val MEDIA_TYPE_PNG = MediaType.parse("image/png")

            val req = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart(
                    "myfile",
                    "newImg.png",
                    RequestBody.create(MEDIA_TYPE_PNG, file)
                ).build()

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .post(req)
                .build()

            val response = client.newCall(request).execute()
            if(response.isSuccessful){
                return response.body()!!.string()
            }
        }
        catch (e: Throwable){
            return "404"
        }
        finally {
            //file.delete()
        }
        return ""
    }
}
