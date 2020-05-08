package com.ncorti.kotlin.template.app.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
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

fun ImageView.loadImageUri(imageUri: Uri) {
    Glide.with(this.context).load(imageUri).into(this)
}
