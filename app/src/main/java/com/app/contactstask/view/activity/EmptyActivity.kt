package com.app.contactstask.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.contactstask.R
import kotlinx.android.synthetic.main.empty_layout.*


class EmptyActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empty_layout)
        clickListeners()
    }


    fun clickListeners() {
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_back -> {
                onBackPressed()
            }
        }

    }

}