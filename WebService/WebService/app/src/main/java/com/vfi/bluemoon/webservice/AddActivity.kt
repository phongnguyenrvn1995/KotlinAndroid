package com.vfi.bluemoon.webservice

import android.content.Context
import android.os.Bundle
import com.vfi.bluemoon.webservice.presenter.AddPresenter
import com.vfi.bluemoon.webservice.view.base.BaseActivity
import com.vfi.bluemoon.webservice.view.ui.AddUI
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : BaseActivity<AddUI>(), AddUI {
    private val subPresenter = AddPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupPresenter()
    }

    override fun initView() {
        btn_back.setOnClickListener {
            subPresenter.fisnish()
        }
        btn_comfirm.setOnClickListener {
            subPresenter.addModel(
                txt_model_name.text.toString(),
                txt_model_birth.text.toString(),
                txt_model_url_img.text.toString()
            )
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun setupPresenter() {
        presenter = subPresenter
        presenter!!.attactUI(this)
        presenter!!.doFirstInit()
    }
}
