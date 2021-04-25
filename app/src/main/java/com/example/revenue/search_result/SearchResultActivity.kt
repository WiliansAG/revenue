package com.example.revenue.search_result

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.base.BaseActivity
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.revenue.RevenueAdapter
import com.example.revenue.revenue.SearchResultPresenter

class SearchResultActivity : BaseActivity(), SearchResultContract.View {
    var rcv:RecyclerView?=null
    var rcvAdapter:SearchResultAdapter?=null
    var presenter:SearchResultPresenter?=null
    var linearLayoutManager: LinearLayoutManager?=null
    var searchView:androidx.appcompat.widget.SearchView?=null
    var originalModel: MutableList<RevenueModel>?=null
    var filtered: ArrayList<RevenueModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val queryResult = intent.getStringExtra("query")
        presenter = SearchResultPresenter(this)
        presenter?.searches(this,queryResult)

        searchView = findViewById(R.id.act_search_result_search_view)
        searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView!!.clearFocus()
                if (query != null) {
                    presenter?.searches(applicationContext,query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        rcv = findViewById(R.id.act_search_result_rcv)
        rcvAdapter = SearchResultAdapter(this, ArrayList<RevenueModel>(),ArrayList<RevenueModel>())
        linearLayoutManager = LinearLayoutManager(this)
        rcv?.adapter = rcvAdapter
        rcv?.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onFailure(message: String?) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

    override fun onSuccess(revenueList: MutableList<RevenueModel>) {
        rcvAdapter?.update(revenueList)
        presenter?.getAll(applicationContext)
    }

    override fun onSuccessAll(revenueList: MutableList<RevenueModel>) {
        originalModel = revenueList
    }

    override fun showError(messageRes: Int) {
        TODO("Not yet implemented")
    }
}