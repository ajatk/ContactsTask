package com.app.contactstask.view.activity

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
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.contactstask.R
import com.app.contactstask.view.model.ContactListModel
import com.app.contactstask.view.adapter.ContactsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class Contacts2Activity : AppCompatActivity() {

    var contactsAdapter: ContactsAdapter? = null
    val READ_CONTACTS_PERMISSIONS_REQUEST = 10
    var contactArray: ArrayList<ContactListModel>? = null
    var nameArray: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameArray = ArrayList()
        contactArray = ArrayList()
        getPermissionToReadUserContacts()


    }

    fun setAdapter(contactModelArrayList: ArrayList<ContactListModel>?) {
        contactsAdapter = ContactsAdapter(this, contactModelArrayList)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_contacts.layoutManager = linearLayoutManager
        rv_contacts.adapter = contactsAdapter

    }


    fun getAllContacts() {
        val phones: Cursor = this.getContentResolver().query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )!!

        while (phones.moveToNext()) {
            val name =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contactListModel =
                ContactListModel()
            if (nameArray!!.contains(name)) {
                for (i in contactArray!!.indices) {
                    var contactName: String = contactArray!!.get(i).getName()!!
                    var contactNumber: String = contactArray!!.get(i).getNumber()!!

                    if (contactName == name) {
                        val upgradeCheckNumber = contactNumber.replace(" ", "")
                        val oldNumber = phoneNumber.replace(" ", "")
                        contactNumber = if (upgradeCheckNumber.contains(oldNumber)) {
                            upgradeCheckNumber
                        } else {
                            "$oldNumber,$upgradeCheckNumber"
                        }
                        contactName = contactName
                        contactArray!!.removeAt(i)
                        contactListModel.setName(contactName)
                        contactListModel.setNumber(contactNumber)
                        contactArray!!.add(contactListModel)
                    }
                }
            } else {
                nameArray!!.add(name)
                contactListModel.setName(name)
                contactListModel.setNumber(phoneNumber)
                contactArray!!.add(contactListModel)
            }
        }
        phones.close()
        setAdapter(contactArray)
    }


    fun getPermissionToReadUserContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.READ_CONTACTS
                    )
                ) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setTitle("Read Contacts permission")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("Please enable access to contacts.")
                    builder.setOnDismissListener(DialogInterface.OnDismissListener {
                        requestPermissions(
                            arrayOf(Manifest.permission.READ_CONTACTS),
                            READ_CONTACTS_PERMISSIONS_REQUEST
                        )
                    })
                    builder.show()
                } else {
                    requestPermissions(
                        this, arrayOf(Manifest.permission.READ_CONTACTS),
                        READ_CONTACTS_PERMISSIONS_REQUEST
                    )
                }
            } else {
                getAllContacts()
            }
        } else {
            getAllContacts()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_CONTACTS_PERMISSIONS_REQUEST ->
                if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAllContacts()
                } else {
                    Snackbar.make(
                        this.findViewById(android.R.id.content),
                        "Please Grant Permissions to fetch contacts",
                        Snackbar.LENGTH_LONG
                    )
                        .setActionTextColor(this.resources.getColor(R.color.colorPrimary))
                        .setDuration(1000)
                        .setAction("Settings",
                            object : View.OnClickListener {
                                @Override
                                override fun onClick(v: View) {
                                    var intent =
                                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                    var uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri)
                                    startActivityForResult(
                                        intent,
                                        READ_CONTACTS_PERMISSIONS_REQUEST
                                    )

                                }
                            })
                        .show()
                }
        }
    }


}
