package com.example.revenue.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R

class FilterAdapter(
        private  val context:FilterActivity,
        private val model: ArrayList<String>,
        private val onClick: (teste: String) -> Unit
    ) : RecyclerView.Adapter<FilterAdapter.FilterHolder>()
{
    var mContext = context

    class FilterHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val check: CheckBox

        init {
            check = itemView.findViewById(R.id.adp_filter_checkbok)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAdapter.FilterHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_filter, parent, false)
        return FilterHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: FilterAdapter.FilterHolder, position: Int) {
        holder.check.setText(model.get(position))
        onClick.invoke("teste")
        holder.itemView.setOnClickListener{
            mContext.selectedList?.append(model.get(position))
        }
    }
    fun update(itemList: ArrayList<String>) {
        model.clear()
        model.addAll(itemList)
        notifyDataSetChanged()
    }
}