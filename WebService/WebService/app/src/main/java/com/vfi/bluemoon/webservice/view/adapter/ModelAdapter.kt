package com.vfi.bluemoon.webservice.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vfi.bluemoon.webservice.R
import com.vfi.bluemoon.webservice.model.Model

/**
 * Created by Blue Moon on 27,January,2021
 */
class ModelAdapter(var context: Context, var listModel: MutableList<Model>) :
    RecyclerView.Adapter<ModelAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val modelHolder: View = inflater.inflate(R.layout.item_model, parent, false)
        val viewHolder = ViewHolder(modelHolder)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Model = listModel[position]
        holder.txt_name.text = model.name
        holder.txt_birth.text = model.birthDay
        holder.btn_delete.setOnClickListener { v: View ->
            onItemClickListener?.onClick(v, position)
        }
        holder.btn_update.setOnClickListener { v: View ->
            onItemClickListener?.onClick(v, position)
        }
        Picasso.get()
            .load(
                when (model.imgUrl) {
                    "" -> "url"
                    else -> model.imgUrl
                }
            )
            .error(android.R.drawable.ic_delete)
            .into(holder.img)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView = view.findViewById(R.id.img_item)
        var txt_name: TextView = view.findViewById(R.id.txt_item_name)
        var txt_birth: TextView = view.findViewById(R.id.txt_item_birth)
        var btn_delete: ImageButton = view.findViewById(R.id.btn_item_delete)
        var btn_update: ImageButton = view.findViewById(R.id.btn_item_update)
    }


}