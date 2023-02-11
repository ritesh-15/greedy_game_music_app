package com.example.musicapp.utils

import okhttp3.ResponseBody
import retrofit2.Response

sealed class Resource<T>(
    val data: T? = null,
    val response: Response<T>? = null,
    val message:String? = null,
    val statusCode:Int? = null,
    val errorBody: ResponseBody? = null
) {

    class Success<T>(data: T?, response: Response<T>? = null) : Resource<T>(data, response)

    class Loading<T>(data: T?) : Resource<T>(data)

    class Error<T>(message:String? = null, statusCode:Int? = null ,data:T? = null,errorBody:ResponseBody? = null) :
        Resource<T>(data,null,message,statusCode)
}