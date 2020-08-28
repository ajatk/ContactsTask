package com.example.rajatkumar.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.contactstask.view.model.OnUserIDResponse
import com.example.rajatkumar.networkInterface.WebApiService

class ApiPageVM :  ViewModel() {
    private val mService  =  WebApiService()
    val repository = ApiPageRepository()

    fun getUserData(hashMap: HashMap<String,String>,context : Context) : MutableLiveData<OnUserIDResponse>? {
        Log.e("getAndroidData","yes")

       // return mService.loadAndroidData(hashMap,context)
        return repository.changeState(hashMap)
    }


}