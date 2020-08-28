package com.example.rajatkumar.viewModel

import androidx.lifecycle.MutableLiveData
import com.app.contactstask.view.model.OnUserIDResponse
import com.example.rajatkumar.networkInterface.WebApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ApiPageRepository {
    var liveCategoryListResponse: MutableLiveData<OnUserIDResponse> = MutableLiveData()
    fun changeState(hashMap: HashMap<String, String>) :  MutableLiveData<OnUserIDResponse> {
        with(WebApiService) {
            create().getResponse(hashMap).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    if (it.body() != null) {
                        liveCategoryListResponse.value = it.body()
                    }
                }, {

                    it.printStackTrace()
                    //Global_utility.hideDialog(context);
                })


            return liveCategoryListResponse
        }
    }
}