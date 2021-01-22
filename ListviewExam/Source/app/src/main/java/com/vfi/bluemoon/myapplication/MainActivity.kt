package com.vfi.bluemoon.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list : ArrayList<String> = arrayListOf("1", "2", "3")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
    }

    private fun load(){
        listView.adapter = ArrayAdapter(this,  android.R.layout.simple_expandable_list_item_1, list)
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, list.get(position), Toast.LENGTH_LONG).show()
        }
    }
}
