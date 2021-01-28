package com.vfi.bluemoon.webservice.presenter

import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.presenter.base.BasePresenter
import com.vfi.bluemoon.webservice.task.TaskAddModel
import com.vfi.bluemoon.webservice.view.ui.AddUI

/**
 * Created by Blue Moon on 27,January,2021
 */
class AddPresenter : BasePresenter<AddUI>() {
    override fun doFirstInit() {
        ui!!.initView()
    }

    fun addModel(name: String, birth: String, url: String) {
        var model: Model? = Model(0, name, birth, url)
        TaskAddModel(ui!!.getContext()).asyncExecute(model)
            .doOnNext { model ->
                ui!!.showToast("addModel success $model")
            }
            .doOnError { thew ->
                ui!!.showToast("addModel fail $thew")
            }
            .subscribe({}, {thew ->  ui!!.showToast("addModel fail $thew")})
    }

    fun fisnish() {
        ui!!.customfinishActivity()
    }
}