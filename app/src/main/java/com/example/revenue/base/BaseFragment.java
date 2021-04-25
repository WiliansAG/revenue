package com.example.revenue.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.revenue.dialogs.ErrorDialog;
import com.example.revenue.dialogs.LoadingDialog;

public abstract class BaseFragment extends Fragment implements IBaseView{

    protected SwipeRefreshLayout swipeRefreshLayout;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getContext());
    }

    @Override
    public void showProgress(boolean show) {
        if (loadingDialog == null) {
            return;
        }
        if (show && !loadingDialog.isShowing()) {
            loadingDialog.show();
        } else if (!show && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        if(message == null){
            new ErrorDialog(getContext()).show();
        }else{
            new ErrorDialog(getContext(),message).show();
        }
    }
}

