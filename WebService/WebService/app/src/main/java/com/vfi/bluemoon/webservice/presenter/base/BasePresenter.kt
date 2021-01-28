package com.vfi.bluemoon.webservice.presenter.base

import com.vfi.bluemoon.webservice.view.ui.UI

/**
 * Created by Blue Moon on 27,January,2021
 */
abstract class BasePresenter<T : UI> {
    protected var ui: T? = null
    abstract fun doFirstInit()
    fun attactUI(ui: T) {
        this.ui = ui
    }

    fun detactUI() {
        this.ui = null
    }
}