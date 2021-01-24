package com.vfi.bluemoon.switchactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        btn.setOnClickListener{
            val intent : Intent = Intent(this@MainActivity, Main2Activity::class.java)
            val arr : IntArray = intArrayOf(1, 2, 3, 4)
            val data : Data = Data(1, "PHONG")
            intent.putExtra("data", data)
            startActivity(intent)
        }
    }
}
