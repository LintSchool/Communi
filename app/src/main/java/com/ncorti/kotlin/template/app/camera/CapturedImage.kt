package com.ncorti.kotlin.template.app.camera

import android.net.Uri

data class CapturedImage(
    var id: Int,
    var imagePath: Uri
)
