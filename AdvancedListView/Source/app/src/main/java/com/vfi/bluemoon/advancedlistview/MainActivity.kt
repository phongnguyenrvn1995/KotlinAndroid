package com.vfi.bluemoon.advancedlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
    }

    fun load(){
        var listFood : ArrayList<Food> = ArrayList()
        listFood.add(Food("1", R.drawable.cry))
        listFood.add(Food("2", R.drawable.cry))
        listFood.add(Food("3", R.drawable.cry))
        listFood.add(Food("4", R.drawable.cry))
        listFood.add(Food("5", R.drawable.cry))

        list.adapter = CustomApdapter(this@MainActivity, listFood)
    }
}
