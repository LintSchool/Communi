package com.ncorti.kotlin.template.app.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(
    snackbarText: String,
    actionText: String,
    actionListener: () -> Unit,
    timeLength: Int = Snackbar.LENGTH_LONG
) {
    val snackbar = Snackbar.make(this, snackbarText, timeLength).setAction(
        actionText
    ) { actionListener() }

    return snackbar.show()
}
