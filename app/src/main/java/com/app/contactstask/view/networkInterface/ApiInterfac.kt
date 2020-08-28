package com.example.rajatkumar.networkInterface

import com.app.contactstask.view.model.OnUserIDResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterfac {
//    fun getRegister(@FieldMap hashMap: HashMap<String, String>): Call<RegisterResponse>
@FormUrlEncoded
@POST("userId")
fun getResponse(@FieldMap hashMap: HashMap<String, String>): Single<Response<OnUserIDResponse>>

}