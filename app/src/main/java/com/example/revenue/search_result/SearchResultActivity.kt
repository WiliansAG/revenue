package com.example.revenue.search_result

import android.content.Intent
import android.os.Bundle
import android.widget.Filter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revenue.R
import com.example.revenue.base.BaseActivity
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.filter.FilterActivity
import com.example.revenue.revenue.RevenueAdapter
import com.example.revenue.revenue.SearchResultPresenter
import java.lang.StringBuilder

class SearchResultActivity : BaseActivity(), SearchResultContract.View {
    var rcv:RecyclerView?=null
    var rcvAdapter:SearchResultAdapter?=null
    var presenter:SearchResultPresenter?=null
    var linearLayoutManager: LinearLayoutManager?=null
    var searchView:androidx.appcompat.widget.SearchView?=null
    var originalModel: MutableList<RevenueModel>?=null
    var imgFilter: ImageView?=null
    var back:ImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        presenter = SearchResultPresenter(this)

        var queryResult = intent.getStringExtra("query")
        var filterResultList = intent.getStringArrayListExtra("filterResult")
        var intent = getIntent()
        var filterResult = StringBuilder()

        if(queryResult == null){
            queryResult = "-"
        }

        if(filterResultList != null){
            filterResultList.forEachIndexed{index,element ->
                if(index == filterResultList.size-1){
                    filterResult.append(element)
                }else{
                    filterResult.append(element+",")
                }

            }
            presenter?.searches(this,queryResult, filterResult.toString())
        }



        presenter?.searches(this,queryResult, filterResult.toString())
        back = findViewById(R.id.act_search_result_back)
        back?.setOnClickListener {
            onBackPressed()
        }

        imgFilter = findViewById(R.id.act_search_result_filter_btn);
        searchView = findViewById(R.id.act_search_result_search_view)
        searchView?.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView!!.clearFocus()
                if (query != null) {
                    presenter?.searches(applicationContext,query, filterResult.toString())
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
        setClick()
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

    fun setClick(){
        imgFilter?.setOnClickListener {
            var intent = Intent(this,FilterActivity::class.java)
            startActivity(intent)
        }
    }
}