package com.app.contactstask.view.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.contactstask.R
import com.app.contactstask.view.model.ContactListModel
import com.app.contactstask.view.adapter.ContactsAdapter
import com.example.android.exampleTest.ContactViewModel
import com.example.android.exampleTest.MainAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class ContactsPageFragment : Fragment() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private lateinit var contactViewModel: ContactViewModel
    var recyclerView : RecyclerView?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_main, container, false)

        contactViewModel = ViewModelProvider(activity!!).get(ContactViewModel::class.java)
         recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        requestContactPermission()
        return  view
    }

    private fun setAllData() {

        val adapter = MainAdapter(activity!!)

        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = LinearLayoutManager(activity!!)


        contactViewModel.allContacts.observe(activity!!, Observer { contact ->
            contact?.let { adapter.setWords(it) }
            if (contact.isNullOrEmpty())
                contactViewModel.getContactList()
        })
    }

    fun requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,
                        Manifest.permission.READ_CONTACTS)) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("Read Contacts permission")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("Please enable access to contacts.")
                    builder.setOnDismissListener({ requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_PERMISSIONS_REQUEST_CODE) })
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(
                        activity!!, arrayOf(Manifest.permission.READ_CONTACTS),
                        REQUEST_PERMISSIONS_REQUEST_CODE
                    )
                }
            } else {
                setAllData()
            }
        } else {
            setAllData()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setAllData()
                } else {
                    Toast.makeText(activity!!, "You have disabled a contacts permission", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}
