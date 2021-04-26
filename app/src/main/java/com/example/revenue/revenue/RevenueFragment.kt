package com.example.revenue.revenue

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.base.BaseFragment
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.search_result.SearchResultActivity
import kotlinx.android.synthetic.main.fragment_revenue.*
import java.io.Console
import java.util.*
import kotlin.collections.ArrayList


class RevenueFragment : BaseFragment(), RevenueContract.View{

    var listAdapter:RevenueAdapter?=null
    var linearLayoutManager:LinearLayoutManager?=null
    var rcv:RecyclerView?=null
    var searchView:androidx.appcompat.widget.SearchView?=null
    var savedModel:MutableList<RevenueModel>?=null
    var presenter:RevenuePresenter?=null
    var dummy:List<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dummy = Collections.emptyList()
        presenter = RevenuePresenter(this)
        presenter?.recepts(context, listOf("") as java.util.ArrayList<String>)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view?.findViewById(R.id.frg_revenue_search_bar)


        rcv = view?.findViewById(R.id.frg_revenue_rcv)
        listAdapter = RevenueAdapter(this, ArrayList<RevenueModel>(),ArrayList<RevenueModel>())
        linearLayoutManager = LinearLayoutManager(context)
        rcv?.layoutManager = linearLayoutManager
        rcv?.adapter = listAdapter

        searchView?.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                presenter?.recepts(context, listOf("") as java.util.ArrayList<String>)
                return false
            }

        })
        searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView!!.clearFocus()
                if (query != null) {
                    var filteredList = listAdapter?.getFilter(query)
                    if(filteredList != null){
                        val intent = Intent(context, SearchResultActivity::class.java)
                        intent.putExtra("query",query)
                        startActivity(intent)

//                        listAdapter?.update(filteredList)
                    }else{
                        Toast.makeText(context,"Não foi encontrado",Toast.LENGTH_LONG).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_revenue, container, false)
    }

    override fun onFailure(message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(revenueList: MutableList<RevenueModel>) {
        listAdapter?.update(revenueList)
        savedModel = revenueList
    }

    override fun showError(messageRes: Int) {
        Toast.makeText(context,"teste",Toast.LENGTH_LONG).show()
    }

}