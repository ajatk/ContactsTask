package com.example.rajatkumar.networkInterface

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.contactstask.view.model.OnUserIDResponse

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WebApiService {
    companion object Factory {
        var BASE_URL = "https://loccam.com/rest_api_chat/api_rest.php/?method=notebook_fetch&/"

        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterfac {
            //Log.e("retrofit","create")
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okhttpClient = OkHttpClient.Builder().addInterceptor(logging)
            //okhttpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okhttpClient.build())
                    .build()

            return retrofit.create(ApiInterfac::class.java)
        }
    }


    private var liveUserResponse: MutableLiveData<OnUserIDResponse> = MutableLiveData()

    /*fun loadAndroidData(hashMap: HashMap<String , String>,context: Context): MutableLiveData<OnUserIDResponse>? {



        create().getResponse(hashMap).enqueue(object : Callback<OnUserIDResponse> {
            override fun onFailure(call: Call<OnUserIDResponse>, t: Throwable?) {
                Log.e("on Failure :", "retrofit error")
            }
            override fun onResponse(call: Call<OnUserIDResponse>, response: retrofit2.Response<OnUserIDResponse>) {

                if (response.isSuccessful) {
                    liveUserResponse.value = response.body()

                }
            }

        })

        return liveUserResponse
    }
*/
}