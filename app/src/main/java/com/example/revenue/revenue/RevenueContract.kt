package com.example.revenue.revenue

import android.content.Context
import com.example.revenue.base.IBasePresenter
import com.example.revenue.base.IBaseView
import com.example.revenue.data.models.RevenueModel

class RevenueContract {
    interface View : IBaseView {
        fun onFailure(message: String?)
        fun onSuccess(revenueList: MutableList<RevenueModel>)
    }

    internal interface Presenter :
        IBasePresenter<View?> {
        fun recepts(context: Context?,dummy:ArrayList<String>)
    }
}