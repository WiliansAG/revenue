package com.example.revenue.revenue

import android.content.Context
import com.example.revenue.base.BasePresenter
import com.example.revenue.data.DataCallbacks.RevenueCallback
import com.example.revenue.data.DataManager.GetRevenues.getRevenue
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.search_result.SearchResultActivity
import com.example.revenue.search_result.SearchResultContract
import okhttp3.ResponseBody

class SearchResultPresenter(view: SearchResultActivity) :
    BasePresenter<SearchResultContract.View?>(),
    SearchResultContract.Presenter {

    init {
        this.view = view
    }

    override fun searches(context: Context?, query: String, filterResult: String) {
        if (view == null) {
            return
        }
        view!!.showProgress(true)
        getRevenue(context, 1, filterResult, query, object : RevenueCallback {
            override fun onFailure(message: String) {
                view!!.showProgress(false)
                view!!.onFailure(message)
            }

            override fun onFailure(e: Throwable) {
                view!!.showProgress(false)
                view!!.onFailure("ERRO")
            }

            override fun onFailure(errorBody: ResponseBody) {
                view!!.showProgress(false)
                view!!.onFailure("ERRO")
            }

            override fun onSuccess(revenueList: MutableList<RevenueModel>) {
                view!!.showProgress(false)
                view!!.onSuccess(revenueList)
            }
        })
    }

    override fun getAll(context: Context?) {
        if(view == null){
            return
        }
        getRevenue(context, 1, "", "", object : RevenueCallback {
            override fun onFailure(message: String) {
                view!!.showProgress(false)
                view!!.onFailure(message)
            }

            override fun onFailure(e: Throwable) {
                view!!.showProgress(false)
                view!!.onFailure("ERRO")
            }

            override fun onFailure(errorBody: ResponseBody) {
                view!!.showProgress(false)
                view!!.onFailure("ERRO")
            }

            override fun onSuccess(revenueList: MutableList<RevenueModel>) {
                view!!.showProgress(false)
                view!!.onSuccessAll(revenueList)
            }
        })
    }


}