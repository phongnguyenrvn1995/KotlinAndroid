package com.vfi.bluemoon.readurl

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settings : WebSettings  = web.settings
        settings.javaScriptEnabled = true
        readURLAsynctask.execute("https://www.google.com")
    }

    var readURLAsynctask = @SuppressLint("StaticFieldLeak")
    object : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            var content: StringBuilder = StringBuilder()
            var url = URL(params[0])
            var connection : HttpsURLConnection = url.openConnection() as HttpsURLConnection
            var inputStream = BufferedReader(InputStreamReader(connection.inputStream))
            var str : String?
            do {
                str = inputStream.readLine()
                if (str != null) {
                    content.append(str)
                }
            } while (str != null)
            return content.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            web.loadData(result, "text/html; charset=utf-8", "UTF-8")
        }
    }
}
