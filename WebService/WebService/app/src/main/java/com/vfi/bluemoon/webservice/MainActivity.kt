package com.vfi.bluemoon.webservice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.presenter.MainPresenter
import com.vfi.bluemoon.webservice.view.adapter.ModelAdapter
import com.vfi.bluemoon.webservice.view.base.BaseActivity
import com.vfi.bluemoon.webservice.view.ui.MainUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainUI>(), MainUI {
    val subPresenter = MainPresenter()
    var modelAdapter: ModelAdapter? = null
    val mutableList: MutableList<Model> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPresenter()
    }

    override fun initView() {
        ///actionBar!!.title = getString(R.string.act_bar_list_model)
        btn_load.setOnClickListener {
            subPresenter.loadListModel()
        }
        btn_add.setOnClickListener {
            subPresenter.startAddActivity()
        }
        modelAdapter = ModelAdapter(this, mutableList)
        recycle_view.layoutManager = LinearLayoutManager(this)
        recycle_view.adapter = modelAdapter
        modelAdapter!!.onItemClickListener = object : ModelAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                when (view.id) {
                    R.id.btn_item_delete -> {
                        subPresenter.doDelete(position)
                    }
                    R.id.btn_item_update -> {
                        subPresenter.doUpdate(position)
                    }
                }
            }
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun items(): MutableList<Model>? {
        return mutableList
    }

    override fun showListModel(list: MutableList<Model>) {
        mutableList.clear()
        mutableList.addAll(0, list)
        modelAdapter!!.notifyDataSetChanged()
    }

    override fun setupPresenter() {
        presenter = subPresenter
        presenter!!.attactUI(this)
        presenter!!.doFirstInit()
    }
}
