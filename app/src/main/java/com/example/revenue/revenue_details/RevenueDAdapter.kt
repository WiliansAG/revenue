package com.example.revenue.revenue_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue.RevenueFragment
import kotlinx.android.synthetic.main.adapter_revenue_details_ingredient.view.*

class RevenueDAdapter(
    private val context: RevenueDetailsActivity,
    private var model: List<String>
):RecyclerView.Adapter<RevenueDAdapter.RevenueDHolder>() {

    fun update(item: List<String>){
        model = item
        notifyDataSetChanged()
    }

    inner class RevenueDHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        var ingredient: TextView
        init {
            ingredient = itemView.findViewById(R.id.adp_revenue_details_ingredient_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueDHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_revenue_details_ingredient, parent, false)
        return RevenueDHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: RevenueDHolder, position: Int) {
        holder.ingredient.setText("."+model.get(position))
    }
}