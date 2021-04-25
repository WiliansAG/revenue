package com.example.revenue.data.DataCallbacks;

import com.example.revenue.data.models.RevenueModel;

import java.util.List;

import okhttp3.ResponseBody;

public interface RevenueCallback {
    void onFailure(String message);

    void onFailure(Throwable e);

    void onFailure(ResponseBody errorBody);

    void onSuccess(List<RevenueModel> revenueList);
}
