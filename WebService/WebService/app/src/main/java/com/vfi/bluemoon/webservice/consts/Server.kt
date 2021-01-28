package com.vfi.bluemoon.webservice.consts

/**
 * Created by Blue Moon on 27,January,2021
 */
class Server {
    companion object{
        val IP_HOST : String = "http://18.224.139.26"
        val PORT_HOST : Int = 8080
        val API_MODEL_GET_ALL : ArrayList<String> = arrayListOf("ModelManager", "rest", "ModelService", "gets")
        val API_MODEL_ADD : ArrayList<String> = arrayListOf("ModelManager", "rest", "ModelService", "add")
        val API_MODEL_UPDATE : ArrayList<String> = arrayListOf("ModelManager", "rest", "ModelService", "update")
        val API_MODEL_DELETE : ArrayList<String> = arrayListOf("ModelManager", "rest", "ModelService", "delete")

    }
}