package com.example.revenue.base;


public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();
}

