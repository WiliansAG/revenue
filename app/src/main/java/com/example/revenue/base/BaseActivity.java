package com.example.revenue.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.revenue.dialogs.ErrorDialog;
import com.example.revenue.dialogs.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    public void showProgress(final boolean show) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog == null) {
                    return;
                }
                if (show && !loadingDialog.isShowing()) {
                    loadingDialog.show();
                } else if (!show && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void showError(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message == null) {
                    new ErrorDialog(getApplicationContext()).show();
                } else {
                    new ErrorDialog(getApplicationContext(), message).show();
                }
            }
        });
    }
}