package com.example.revenue.revenue

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue.RevenueAdapter.RevenueHolder
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class RevenueAdapter(
    private val context: RevenueFragment,
    private var model: MutableList<RevenueModel>,
    private var modelFilter: ArrayList<RevenueModel>
) :
    RecyclerView.Adapter<RevenueHolder>() {


    fun update(itemList: MutableList<RevenueModel>) {
        model.clear()
        model = itemList
        notifyDataSetChanged()
    }

    fun getFilter(query: String): ArrayList<RevenueModel> {
        for(i in 0 until model.size){
            if(model.get(i).title.contains(query)){
                modelFilter.add(model.get(i))
            }
        }
        return modelFilter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevenueHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_revenue, parent, false)
        return RevenueHolder(itemView)
    }

    override fun onBindViewHolder(holder: RevenueHolder, position: Int) {

        Picasso.get()
            .load(model[position].thumbnail)
            .placeholder(R.color.black)
            .fit()
            .into(holder?.image)
        holder.txt_recipe.text = model[position].title
        //holder.txt_category.setText(model.get(position).getCategory());
    }

    override fun getItemCount(): Int {
        return model.size
    }

    inner class RevenueHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
         val image: ImageView
         val txt_category: TextView
         val txt_recipe: TextView

        init {
            image = itemView.findViewById(R.id.adp_revenue_image)
            txt_category = itemView.findViewById(R.id.adp_revenue_category_name)
            txt_recipe = itemView.findViewById(R.id.adp_revenue_recipe_name)
        }
    }

}