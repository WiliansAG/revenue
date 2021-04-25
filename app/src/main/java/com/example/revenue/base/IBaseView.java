package com.example.revenue.base;

public interface IBaseView {
    void showProgress(boolean show);

    void showError(String message);

    void showError(int messageRes);
}
