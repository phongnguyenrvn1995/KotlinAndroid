package com.vfi.bluemoon.advancedlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Blue Moon on 22,January,2021
 */
class CustomApdapter(
    val context: Context,
    val list: ArrayList<Food>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layoutInflater: LayoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.item_food, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var food: Food = getItem(position) as Food
        viewHolder.txt.text = food.name.toString()
        viewHolder.img.setImageResource(food.img)
        return view as View
    }

    override fun getItem(position: Int): Any = list.get(position)

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = list.size

    class ViewHolder(val row: View) {
        val txt: TextView
        val img: ImageView

        init {
            txt = row.findViewById(R.id.txt)
            img = row.findViewById(R.id.img)
        }
    }
}