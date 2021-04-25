package com.example.revenue.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.revenue.R;

public class LoadingDialog extends ProgressDialog {
    private Context context;

    public LoadingDialog(Context context) {
        super(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        this.context = context;
        setupDialog();
    }

    private void setupDialog() {
        this.setMessage("Carregando");
        this.setIndeterminate(true);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }
}
