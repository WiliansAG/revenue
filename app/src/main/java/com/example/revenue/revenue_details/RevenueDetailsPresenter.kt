package com.example.revenue.revenue_details

import android.content.Context
import com.example.revenue.base.BasePresenter
import com.example.revenue.data.DataCallbacks.RevenueCallback
import com.example.revenue.data.DataManager.GetRevenues
import com.example.revenue.data.models.RevenueModel
import com.google.android.gms.common.api.internal.BasePendingResult
import okhttp3.ResponseBody

class RevenueDetailsPresenter(view: RevenueDetailsActivity): BasePresenter<RevenueDetailsContract.View?>(),
    RevenueDetailsContract.Presenter{

    init {
        this.view = view
    }

    override fun recepts(context: Context?) {
        if (view == null) {
            return
        }
        view!!.showProgress(true)
        GetRevenues.getRevenue(context, 1, "", "", object : RevenueCallback {
            override fun onSuccess(revenueList: MutableList<RevenueModel>?) {
                view!!.showProgress(false)
                view!!.onSucess(revenueList!!)
            }

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


        })
    }
}