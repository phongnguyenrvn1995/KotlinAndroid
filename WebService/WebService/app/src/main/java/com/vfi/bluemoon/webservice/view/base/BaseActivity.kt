package com.vfi.bluemoon.webservice.view.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vfi.bluemoon.webservice.R
import com.vfi.bluemoon.webservice.presenter.base.BasePresenter
import com.vfi.bluemoon.webservice.view.ui.UI

abstract class BaseActivity<T : UI> : AppCompatActivity(), UI {
    protected var presenter: BasePresenter<T>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        setContentView(R.layout.activity_base)
    }

    abstract fun setupPresenter()

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun customStartActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun customfinishActivity() {
        finish()
    }

    override fun showAsk(msg: String, listener: DialogInterface.OnClickListener){
        AlertDialog.Builder(this)
            .setTitle("Ask")
            .setMessage(msg)
            .setPositiveButton("Yes", listener)
            .setNegativeButton("No", null)
            .show()
    }
}
