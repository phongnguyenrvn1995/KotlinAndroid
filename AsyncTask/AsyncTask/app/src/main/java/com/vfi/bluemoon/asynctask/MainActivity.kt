package com.vfi.bluemoon.asynctask

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAsyncTask.execute()
    }

    private val myAsyncTask: AsyncTask<String, String, String> = @SuppressLint("StaticFieldLeak")
    object : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg params: String?): String {
            txt.text = "doInBackground"
            for(i in 0..10){
                publishProgress(i.toString())
                Thread.sleep(1000)
            }
            return "OK"
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            txt.append(values[0].toString())
            txt.append("\n")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            txt.append("onPostExecute")
            txt.append(result)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            txt.append("onPreExecute")
        }
    }
}
