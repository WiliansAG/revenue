package com.example.revenue.revenue_details

import android.content.Context
import com.example.revenue.base.IBasePresenter
import com.example.revenue.base.IBaseView
import com.example.revenue.data.models.RevenueModel

class RevenueDetailsContract {

    interface View : IBaseView {
        fun onFailure(message: String?)
        fun onSucess(list: MutableList<RevenueModel>)
    }

    internal interface Presenter :
        IBasePresenter<View?> {
        fun recepts(context: Context?)
    }
}