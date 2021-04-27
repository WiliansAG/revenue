package com.example.revenue.dialogs

import android.app.AlertDialog
import android.content.Context
import com.example.revenue.R

class ErrorDialog : AlertDialog.Builder {
    constructor(context: Context) : super(
        context,
        R.style.Theme_AppCompat_Light_Dialog_Alert
    ) {
        setupDialog(context.getString(R.string.error_message))
    }

    constructor(context: Context?, message: String) : super(
        context,
        R.style.Theme_AppCompat_Light_Dialog_Alert
    ) {
        setupDialog(message)
    }

    private fun setupDialog(message: String) {
        setTitle("ERRO")
        setMessage(message)
        setPositiveButton(
            android.R.string.ok
        ) { dialog, which -> dialog.dismiss() }
    }
}