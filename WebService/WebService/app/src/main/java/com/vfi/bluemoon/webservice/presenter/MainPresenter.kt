package com.vfi.bluemoon.webservice.presenter

import android.content.DialogInterface
import android.content.Intent
import com.vfi.bluemoon.webservice.AddActivity
import com.vfi.bluemoon.webservice.UpdateActivity
import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.presenter.base.BasePresenter
import com.vfi.bluemoon.webservice.task.TaskDeleteModel
import com.vfi.bluemoon.webservice.task.TaskGetAllModels
import com.vfi.bluemoon.webservice.view.ui.MainUI

/**
 * Created by Blue Moon on 27,January,2021
 */
class MainPresenter : BasePresenter<MainUI>() {
    override fun doFirstInit() {
        ui!!.initView()
    }

    fun loadListModel() {
        TaskGetAllModels(ui!!.getContext()).asyncExecute(null)
            .doOnNext { list ->
                ui!!.showListModel(list)
            }
            .doOnError { thew ->
                ui!!.showToast("loadListModel fail $thew")
            }
            .subscribe({}, { thew -> ui!!.showToast("loadListModel fail $thew") })
    }

    fun doDelete(position: Int) {
        val model = ui!!.items()?.get(position)
        ui!!.showAsk(
            "You want to delete ${model!!.name}",
            DialogInterface.OnClickListener { _, _ ->
                TaskDeleteModel(ui!!.getContext()).asyncExecute(model!!.id)
                    .doOnNext { resStr ->
                        when (resStr) {
                            "00" -> {
                                loadListModel()
                            }
                            else -> {
                                ui!!.showToast("delete fail")
                            }
                        }
                    }
                    .doOnError { thew ->
                        ui!!.showToast("doDelete fail $thew")
                    }
                    .subscribe({}, { thew -> ui!!.showToast("doDelete fail $thew") })
            })
    }

    fun doUpdate(position: Int) {
        startUpdateActivity(position)
    }

    fun startAddActivity() {
        var intent: Intent = Intent(ui!!.getContext(), AddActivity::class.java)
        ui!!.customStartActivity(intent)
    }

    fun startUpdateActivity(position: Int) {
        val model: Model = ui!!.items()!![position]
        var intent: Intent = Intent(ui!!.getContext(), UpdateActivity::class.java)
        intent.putExtra("data", model)
        ui!!.customStartActivity(intent)
    }
}