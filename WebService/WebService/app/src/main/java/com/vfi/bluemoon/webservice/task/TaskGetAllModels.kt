package com.vfi.bluemoon.webservice.task

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.vfi.bluemoon.webservice.consts.Server
import com.vfi.bluemoon.webservice.model.Model
import com.vfi.bluemoon.webservice.task.base.TaskBase
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/**
 * Created by Blue Moon on 27,January,2021
 */
class TaskGetAllModels(override var context: Context) :
    TaskBase<Void, MutableList<Model>>(context) {
    override fun build(input: Void?): Observable<MutableList<Model>> {
        return Observable.create { emitter ->
            var okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(30 * 1000, TimeUnit.MILLISECONDS)
                .build()

            var retrofit: Retrofit = Retrofit.Builder().client(okHttpClient)
                .baseUrl(Server.IP_HOST + ":" + Server.PORT_HOST)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

            var api = retrofit.create(GetALLAPI::class.java)
            var res: Call<List<Model>> = api.loadModels(
                Server.API_MODEL_GET_ALL[0],
                Server.API_MODEL_GET_ALL[1],
                Server.API_MODEL_GET_ALL[2],
                Server.API_MODEL_GET_ALL[3]
            )

            res.enqueue(object : Callback<List<Model>> {
                override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                    Log.d("onFailure", "onFailure")
                    emitter.onError(t)
                    emitter.onComplete()
                }

                override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                    Log.d("onResponse", "onResponse")
                    var list: MutableList<Model> = response.body()!!.toMutableList()
                    if (list == null)
                        emitter.onError(Throwable())
                    else
                        emitter.onNext(list)
                    emitter.onComplete()
                }
            })

        }
    }

    interface GetALLAPI {
        @GET("{m1}/{m2}/{m3}/{m4}")
        fun loadModels(
            @Path("m1") m1: String,
            @Path("m2") m2: String,
            @Path("m3") m3: String,
            @Path("m4") m4: String
        ): Call<List<Model>>
    }
}