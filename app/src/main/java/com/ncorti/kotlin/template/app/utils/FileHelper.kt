package com.ncorti.kotlin.template.app.utils

import android.content.Context
import android.os.Environment
import java.io.File

private const val IMAGE_PREFIX = "Image_"
private const val JPG_SUFFIX = ".jpg"

fun createFile(context: Context): File {
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("${IMAGE_PREFIX}_${System.currentTimeMillis()}", JPG_SUFFIX, storageDir)
}
