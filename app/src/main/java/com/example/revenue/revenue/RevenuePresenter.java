package com.example.revenue.revenue;

import android.content.Context;

import com.example.revenue.base.BasePresenter;
import com.example.revenue.data.DataCallbacks.RevenueCallback;
import com.example.revenue.data.DataManager.GetRevenues;
import com.example.revenue.data.models.RevenueModel;

import java.util.List;

import okhttp3.ResponseBody;

public class RevenuePresenter extends BasePresenter<RevenueContract.View> implements RevenueContract.Presenter{

    public RevenuePresenter(RevenueContract.View view){
        this.view = view;
    }

    @Override
    public void recepts(Context context,List<String> dummy) {
        if(view == null){
            return;
        }
        view.showProgress(true);
        GetRevenues.INSTANCE.getRevenue(context, 1,dummy,"",new RevenueCallback() {
            @Override
            public void onFailure(String message) {
                view.showProgress(false);
                view.onFailure(message);
            }

            @Override
            public void onFailure(Throwable e) {
                view.showProgress(false);
                view.onFailure("ERRO");
            }

            @Override
            public void onFailure(ResponseBody errorBody) {
                view.showProgress(false);
                view.onFailure("ERRO");
            }

            @Override
            public void onSuccess(List<RevenueModel> revenueList) {
                view.showProgress(false);
                view.onSuccess(revenueList);
            }
        });
    }
}
