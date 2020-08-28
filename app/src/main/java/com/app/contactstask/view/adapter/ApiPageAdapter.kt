package com.app.contactstask.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.contactstask.R
import com.app.contactstask.view.activity.EmptyActivity
import com.app.contactstask.view.model.FetchDatum

class ApiPageAdapter(
    val context: Context,
    var fetchData: MutableList<FetchDatum>

    ) :
    RecyclerView.Adapter<ApiPageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.contacts_item, viewGroup, false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return fetchData!!.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.tv_contact_name!!.text = fetchData!!.get(i).id
        viewHolder.tv_contact_number!!.text = fetchData!!.get(i).userId

        viewHolder.lin_view!!.setOnClickListener { this
        var intent=Intent(context, EmptyActivity::class.java)
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_contact_name: TextView? = null
        var tv_contact_number: TextView? = null
        var lin_view: LinearLayout? = null

        init {
            tv_contact_name = itemView.findViewById(R.id.tv_contact_name)
            tv_contact_number = itemView.findViewById(R.id.tv_contact_number)
            lin_view = itemView.findViewById(R.id.lin_view)
        }

    }
}