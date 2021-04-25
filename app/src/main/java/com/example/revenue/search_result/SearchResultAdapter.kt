package com.example.revenue.search_result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue.RevenueFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_search_result.*


class SearchResultAdapter(
    private val context: SearchResultActivity,
    private var model: MutableList<RevenueModel>,
    private var modelFilter: ArrayList<RevenueModel>
) : RecyclerView.Adapter<SearchResultAdapter.SearchHolder>() {

    class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image:ImageView;
        val recipe:TextView;

        init {
            image = itemView.findViewById(R.id.adp_search_result_image)
            recipe = itemView.findViewById(R.id.adp_search_result_recipe_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_search_result, parent, false)
        return SearchHolder(itemView)
    }

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        if(model[position].thumbnail != ""){
            Picasso.get()
                .load(model[position].thumbnail)
                .placeholder(R.color.black)
                .fit()
                .into(holder?.image)
        }
        if(model[position].title != ""){
            holder.recipe.text = model[position].title
        }
    }

    fun update(itemList: MutableList<RevenueModel>) {
        model.clear()
        model = itemList
        notifyDataSetChanged()
    }

    fun getFilter(query: String, original: MutableList<RevenueModel>): ArrayList<RevenueModel> {
        for(i in 0 until model.size){
            if(original.get(i).title.contains(query)){
                modelFilter.add(original.get(i))
            }
        }
        return modelFilter
    }
}