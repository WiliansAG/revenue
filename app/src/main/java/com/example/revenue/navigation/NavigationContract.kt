package com.example.revenue.navigation

import android.content.Context
import com.example.revenue.base.IBasePresenter
import com.example.revenue.base.IBaseView
import com.example.revenue.data.models.RevenueModel

class NavigationContract {
    interface View : IBaseView {
        fun onFailureNav(message: String?)
        fun onSuccessNav(list: MutableList<RevenueModel>)
    }

    internal interface Presenter :
        IBasePresenter<View?> {
        fun recepts(context: Context?, dummy:String)
    }
}