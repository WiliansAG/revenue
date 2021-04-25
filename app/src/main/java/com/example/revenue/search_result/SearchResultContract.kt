package com.example.revenue.search_result

import android.content.Context
import com.example.revenue.base.IBasePresenter
import com.example.revenue.base.IBaseView
import com.example.revenue.data.models.RevenueModel

class SearchResultContract {

    interface View : IBaseView {
        fun onFailure(message: String?)
        fun onSuccess(revenueList: MutableList<RevenueModel>)
        fun onSuccessAll(revenueList: MutableList<RevenueModel>)
    }

    internal interface Presenter :
        IBasePresenter<View?> {
        fun searches(context: Context?, query: String)
        fun getAll(context: Context?)
    }
}