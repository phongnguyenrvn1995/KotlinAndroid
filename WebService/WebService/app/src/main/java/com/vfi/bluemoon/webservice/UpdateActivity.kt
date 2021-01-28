package com.vfi.bluemoon.webservice

import android.content.Context
import android.os.Bundle
import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.presenter.UpdatePresenter
import com.vfi.bluemoon.webservice.view.base.BaseActivity
import com.vfi.bluemoon.webservice.view.ui.UpdateUI
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : BaseActivity<UpdateUI>(), UpdateUI {
    private val subPresenter = UpdatePresenter()
    private var model: Model? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        model = intent.getSerializableExtra("data") as Model
        setupPresenter()

    }

    override fun initView() {
        txt_model_name.setText(model!!.name)
        txt_model_birth.setText(model!!.birthDay)
        txt_model_url_img.setText(model!!.imgUrl)
        btn_comfirm.setOnClickListener {
            subPresenter.updateModel(
                txt_model_name.text.toString(),
                txt_model_birth.text.toString(),
                txt_model_url_img.text.toString()
            )
        }
        btn_back.setOnClickListener {
            subPresenter.fisnish()
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

    override fun model(): Model {
        return model!!
    }
}
