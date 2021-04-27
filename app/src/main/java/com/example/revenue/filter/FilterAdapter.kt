package com.example.revenue.filter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.MainActivity
import com.example.revenue.R
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue_details.RevenueDetailsActivity

class FilterAdapter(
        private  val context:FilterActivity,
        private val model: ArrayList<String>,
        val onClick: (teste: String) -> Unit
    ) : RecyclerView.Adapter<FilterAdapter.FilterHolder>()
{
    var mContext = context

    inner class FilterHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val check: CheckBox

        init {
            check = itemView.findViewById(R.id.adp_filter_checkbok)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_filter, parent, false)
        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                onClick.invoke("teste")
            }

        })
        return FilterHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.check.setText(model.get(position))

    }
    fun update(itemList: ArrayList<String>) {
        model.clear()
        model.addAll(itemList)
        notifyDataSetChanged()
    }
}