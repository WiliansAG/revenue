package com.example.revenue.favorites

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue_details.RevenueDetailsActivity
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    var model: ArrayList<RevenueModel>
): RecyclerView.Adapter<FavoriteAdapter.FilterHolder>() {

    inner class FilterHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var image:ImageView
        var name:TextView
        init {
            image = itemView.findViewById(R.id.adp_favorite_image)
            name = itemView.findViewById(R.id.adp_favorite_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_favorite, parent, false)
        return FilterHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }


    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        Picasso.get()
            .load(model[position].thumbnail)
            .placeholder(R.color.black)
            .fit()
            .into(holder?.image)
        holder.name.setText(model[position].title)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var act = view?.context
                var intent = Intent(act?.applicationContext, RevenueDetailsActivity::class.java)
                val item = model.get(position)


                intent.putExtra("bundle",item as RevenueModel)
                act?.startActivity(intent)

            }

        })
    }

    fun update(itemList: MutableList<RevenueModel>) {
        model.clear()
        model.addAll(itemList)
        notifyDataSetChanged()
    }
}