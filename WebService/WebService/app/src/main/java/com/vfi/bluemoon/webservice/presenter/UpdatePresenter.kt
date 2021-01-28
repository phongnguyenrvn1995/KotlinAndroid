package com.vfi.bluemoon.webservice.presenter

import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.presenter.base.BasePresenter
import com.vfi.bluemoon.webservice.task.TaskUpdateModel
import com.vfi.bluemoon.webservice.view.ui.UpdateUI

/**
 * Created by Blue Moon on 27,January,2021
 */
class UpdatePresenter : BasePresenter<UpdateUI>() {
    override fun doFirstInit() {
        ui!!.initView()
    }

    fun updateModel(name: String, birth: String, url: String) {
        var model: Model? = Model(ui!!.model().id, name, birth, url)
        TaskUpdateModel(ui!!.getContext()).asyncExecute(model)
            .doOnNext { model ->
                ui!!.showToast("updateModel success $model")
            }
            .doOnError { thew ->
                ui!!.showToast("updateModel fail $thew")
            }
            .subscribe({}, { thew -> ui!!.showToast("updateModel fail $thew") })
    }

    fun fisnish() {
        ui!!.customfinishActivity()
    }
}