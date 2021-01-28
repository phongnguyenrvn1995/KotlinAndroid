package com.vfi.bluemoon.webservice.view.ui

import com.vfi.bluemoon.webservice.model.Model

/**
 * Created by Blue Moon on 27,January,2021
 */
interface MainUI : UI {
    fun showListModel(list: MutableList<Model>)
    fun items(): MutableList<Model>?
}