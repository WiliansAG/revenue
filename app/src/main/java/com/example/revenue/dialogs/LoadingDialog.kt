package com.example.revenue.dialogs

import android.app.ProgressDialog
import android.content.Context
import com.example.revenue.R

class LoadingDialog(private val mcontext: Context) :
    ProgressDialog(mcontext, R.style.Theme_AppCompat_Light_Dialog_Alert) {
    private fun setupDialog() {
        setMessage("Carregando")
        this.isIndeterminate = true
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    init {
        setupDialog()
    }
}