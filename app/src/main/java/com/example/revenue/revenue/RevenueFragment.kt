package com.example.revenue.revenue

import android.content.Context
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
import com.example.revenue.utils.PreferencesManeger
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
    var pref:PreferencesManeger?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dummy = Collections.emptyList()
        presenter = RevenuePresenter(this)
        presenter?.recepts(context, "")

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        rcv = view?.findViewById(R.id.frg_revenue_rcv)
        listAdapter = RevenueAdapter(RevenueFragment(), ArrayList<RevenueModel>(),ArrayList<RevenueModel>())
        linearLayoutManager = LinearLayoutManager(context)
        rcv?.layoutManager = linearLayoutManager
        rcv?.adapter = listAdapter


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

    fun teste(): Context? {
        return context
    }

}