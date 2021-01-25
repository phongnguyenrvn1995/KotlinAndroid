package com.vfi.bluemoon.json

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    var listPosts: MutableList<String?> = ArrayList()
    var listComments: MutableList<String?> = ArrayList()
    var listPostsAdapter: ArrayAdapter<String?>? = null//
    var listCommentsAdapter: ArrayAdapter<String?>? = null//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listPostsAdapter =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listPosts)
        listCommentsAdapter =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listComments)
        list1.adapter =
            listPostsAdapter
        list2.adapter =
            listCommentsAdapter
        load.setOnClickListener {
            GetJsonAsysTask().execute("https://my-json-server.typicode.com/typicode/demo/db")
        }
    }

    inner class GetJsonAsysTask : AsyncTask<String, String, String>() {
        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            when (values[0]) {
                "posts" -> {
                    listPosts.add(values[1])
                }
                "listComments" -> {
                    listComments.add(values[1])
                }
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            listCommentsAdapter!!.notifyDataSetChanged()
            listPostsAdapter!!.notifyDataSetChanged()
        }

        override fun doInBackground(vararg params: String?): String {
            val url = URL(params[0])
            val connection = url.openConnection()
            val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
            var content = StringBuffer()
            var str: String?
            do {
                str = reader.readLine()
                if (str != null) {
                    content.append(str)
                }
            } while (str != null)

            listComments.clear()
            listPosts.clear()
            val job = JSONObject(content.toString())
            val jarr1 = job.getJSONArray("posts")
            for (i in 0 until jarr1.length()) {
                val job_ = jarr1[i] as JSONObject
                publishProgress("posts", job_["id"].toString() + " - " + job_["title"].toString())
            }
            val jarr2 = job.getJSONArray("comments")
            for (i in 0 until jarr2.length()) {
                val job_ = jarr2[i] as JSONObject
                publishProgress(
                    "listComments", job_["id"].toString() +
                            " - " +
                            job_["body"].toString() +
                            " - " +
                            job_["postId"].toString()
                )
            }
            return "OK"
        }
    }
}
