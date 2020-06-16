package com.lintschool.communi.camera

import android.net.Uri

data class CapturedImage(
    var id: Int,
    var imagePath: Uri
)
