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
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

/**
 * Created by Blue Moon on 27,January,2021
 */
class TaskAddModel(override var context: Context) : TaskBase<Model, Model>(context) {
    override fun build(input: Model?): Observable<Model> {
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

            var api = retrofit.create(DelALLAPI::class.java)
            var res: Call<Model> = api.loadModels(
                Server.API_MODEL_ADD[0],
                Server.API_MODEL_ADD[1],
                Server.API_MODEL_ADD[2],
                Server.API_MODEL_ADD[3],
                input!!.name,
                input.birthDay,
                input.imgUrl
            )

            res.enqueue(object : Callback<Model> {
                override fun onFailure(call: Call<Model>, t: Throwable) {
                    Log.d("onFailure", "onFailure")
                    emitter.onError(t)
                    emitter.onComplete()
                }

                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                    Log.d("onResponse", "onResponse")
                    var model = response.body()
                    if (model == null)
                        emitter.onError(Throwable())
                    else
                        emitter.onNext(model)
                    emitter.onComplete()
                }
            })

        }
    }

    interface DelALLAPI {
        @POST("{m1}/{m2}/{m3}/{m4}")
        @FormUrlEncoded
        fun loadModels(
            @Path("m1") m1: String,
            @Path("m2") m2: String,
            @Path("m3") m3: String,
            @Path("m4") m4: String,
            @Field("model_name") model_name: String,
            @Field("model_birthday") model_birthday: String,
            @Field("model_image_url") model_image_url: String
        ): Call<Model>
    }
}