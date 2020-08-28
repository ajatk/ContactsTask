package com.example.rajatkumar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.contactstask.R
import com.app.contactstask.view.adapter.ApiPageAdapter
import com.app.contactstask.view.model.OnUserIDResponse
import com.example.rajatkumar.viewModel.ApiPageVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_api_page.*

class ApiPageFragment : Fragment() {
    private lateinit var mAndroidViewModel: ApiPageVM
    var apiPageAdapter: ApiPageAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view  = inflater.inflate(R.layout.fragment_api_page, container, false)
        mAndroidViewModel = ViewModelProviders.of(activity!!).get(ApiPageVM::class.java)

        val hashMap: HashMap<String, String> = HashMap<String,String>()
        hashMap.put("userId","8872862607")
        mAndroidViewModel.getUserData(hashMap, activity!!)!!.observe(this, Observer<OnUserIDResponse> {
            if (it.hashCode() == 200) {
                apiPageAdapter = ApiPageAdapter(activity!!, it.fetchData)
                val linearLayoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                fragA_recycler.layoutManager = linearLayoutManager
                fragA_recycler.adapter = apiPageAdapter
            }
        })
        return view
    }
}