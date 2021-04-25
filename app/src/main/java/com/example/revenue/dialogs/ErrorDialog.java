package com.example.revenue.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.revenue.R;

public class ErrorDialog extends AlertDialog.Builder{
    public ErrorDialog(Context context) {
        super(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        setupDialog(context.getString(R.string.error_message));
    }

    public ErrorDialog(Context context, String message) {
        super(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        setupDialog(message);
    }

    private void setupDialog(String message) {
        setTitle("ERRO");
        setMessage(message);
        setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
}
