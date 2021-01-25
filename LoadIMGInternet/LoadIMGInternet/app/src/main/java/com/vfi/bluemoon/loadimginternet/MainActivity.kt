package com.vfi.bluemoon.loadimginternet

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadImgAsynsTask.execute("https://kenh14cdn.com/thumb_w/660/203336854389633024/2020/11/28/screen-shot-2020-11-28-at-090813-16065297766611891483033.png")
    }

    var loadImgAsynsTask = @SuppressLint("StaticFieldLeak")
    object:AsyncTask<String, Void, Bitmap>(){
        override fun doInBackground(vararg params: String?): Bitmap {
            val url = URL(params[0])
            val connection = url.openConnection()
            var bmp = BitmapFactory.decodeStream(connection.getInputStream())
            return bmp
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            img.setImageBitmap(result)
        }
    }
}
