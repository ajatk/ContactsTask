package com.app.contactstask.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.app.contactstask.R
import com.app.contactstask.view.fragment.ContactsPageFragment
import com.example.android.exampleTest.Main2Activity
import com.example.rajatkumar.fragment.ApiPageFragment

class MainActivity1 : AppCompatActivity(){
    var tvContacts : TextView? =null
    var tvApiPage : TextView? =null
    var framLay : FrameLayout? =null
    var fragmentActivity: FragmentActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        tvApiPage = findViewById(R.id.tv_apiPage)
        tvContacts = findViewById(R.id.tv_Contacts)
        framLay = findViewById(R.id.frame_layout)

        setoNclicked()
    }

    private fun setoNclicked() {
        tvContacts!!.setOnClickListener {
            framLay!!.visibility = View.VISIBLE

           /* getSupportFragmentManager()
                .beginTransaction().replace(R.id.frame_layout, ContactsPageFragment())
                .addToBackStack(null).commit()*/
            startActivity(Intent(this, Contacts2Activity::class.java))
        }
        tvApiPage!!.setOnClickListener {
            framLay!!.visibility = View.VISIBLE
            getSupportFragmentManager()
                .beginTransaction().add(R.id.frame_layout, ApiPageFragment())
                .addToBackStack(null).commit()
        }
    }
}