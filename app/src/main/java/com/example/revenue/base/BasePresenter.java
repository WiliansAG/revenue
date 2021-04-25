package com.example.revenue.base;

import com.example.revenue.utils.FailureHandler;

import okhttp3.ResponseBody;

public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {

    protected ViewT view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        this.view = null;
    }

    public void handleFailure(){
        if(isViewReady()){
            ((IBaseView) this.view).showProgress(false);
            ((IBaseView) this.view).showError(null);
        }
    }

    public void handleFailure(Throwable t){
        if(isViewReady()){
            ((IBaseView) this.view).showProgress(false);
            ((IBaseView) this.view).showError(FailureHandler.handleThrowable(t));
        }
    }

    public void handleFailure(ResponseBody errorBody){
        if(isViewReady()){
            String message = FailureHandler.handleResonseBody(errorBody);
            ((IBaseView) this.view).showProgress(false);
            ((IBaseView) this.view).showError(message);
        }
    }

    protected boolean isViewReady(){
        return this.view != null && this.view instanceof IBaseView;
    }
}
