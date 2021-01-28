package com.vfi.bluemoon.webservice.task.base

import android.content.Context
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by Blue Moon on 27,January,2021
 */
abstract class TaskBase<IN, OUT>(open var context: Context) {
    val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(30,
        50,
        10,
        TimeUnit.SECONDS,
        LinkedBlockingQueue(),
        object : ThreadFactory {
            override fun newThread(r: Runnable): Thread {
                return Thread(r, "NAME")
            }
        })

    fun execute(input: IN?): OUT {
        return build(input)
            .subscribeOn(Schedulers.from(threadPoolExecutor))
            .observeOn(AndroidSchedulers.mainThread())
            .blockingSingle()
    }

    fun asyncExecute(input: IN?): Observable<OUT> {
        return build(input)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    abstract fun build(input: IN?): Observable<OUT>
}