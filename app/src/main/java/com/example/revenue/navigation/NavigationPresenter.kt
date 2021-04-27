package com.example.revenue.navigation

import android.content.Context
import com.example.revenue.base.BasePresenter
import com.example.revenue.data.DataCallbacks.RevenueCallback
import com.example.revenue.data.DataManager.GetRevenues.getRevenue
import com.example.revenue.data.models.RevenueModel
import okhttp3.ResponseBody

class NavigationPresenter(view: NavigationActivity): BasePresenter<NavigationContract.View>(),NavigationContract.Presenter {

    init {
        this.view = view
    }

    override fun recepts(context: Context?, dummy: String) {
        if (view == null) {
            return
        }
        view.showProgress(true)
        getRevenue(context, 1, "", "", object : RevenueCallback {
            override fun onFailure(message: String) {
                view.showProgress(false)
                view.onFailureNav(message)
            }

            override fun onFailure(e: Throwable) {
                view.showProgress(false)
                view.onFailureNav("ERRO")
            }

            override fun onFailure(errorBody: ResponseBody) {
                view.showProgress(false)
                view.onFailureNav("ERRO")
            }

            override fun onSuccess(revenueList: MutableList<RevenueModel>) {
                view.showProgress(false)
                view.onSuccessNav(revenueList)
            }
        })
    }
}