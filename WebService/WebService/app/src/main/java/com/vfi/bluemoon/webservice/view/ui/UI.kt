package com.vfi.bluemoon.webservice.view.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent

/**
 * Created by Blue Moon on 27,January,2021
 */
interface UI {
    fun initView()
    fun getContext(): Context
    fun showToast(msg: String)
    fun showAsk(msg: String, listener : DialogInterface.OnClickListener)
    fun customStartActivity(intent: Intent)
    fun customfinishActivity()
}