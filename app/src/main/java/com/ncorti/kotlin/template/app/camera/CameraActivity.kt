package com.ncorti.kotlin.template.app.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.TextureView
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.utils.showSnackbar
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    val CAMERA_PERMISSION = Manifest.permission.CAMERA
    val CAMERA_PERMISSION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        setup()
    }

    fun setup() {

        if (ActivityCompat.checkSelfPermission(
                this,
                CAMERA_PERMISSION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CAMERA_PERMISSION),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            camera_preview.post { startCamera() }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            camera_preview.post { startCamera() }
        } else {
            camera_activity_root.showSnackbar(
                getString(R.string.camera_permission_snackbar_text),
                getString(R.string.turn_on), {
                    val uri = Uri.fromParts("package", packageName, null)
                    val intent = Intent().apply {
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = uri
                    }
                    startActivity(intent)
                })
        }
    }


    private fun startCamera() {


        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                // We don't set a resolution for image capture; instead, we
                // select a capture mode which will infer the appropriate
                // resolution based on aspect ration and requested mode
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
            }.build()

        // Build the image capture use case and attach button click listener
        val imageCapture = ImageCapture(imageCaptureConfig)
        capture_btn.setOnClickListener {
            val file = File(externalMediaDirs.first(),
                "${System.currentTimeMillis()}.jpg")

            imageCapture.takePicture(file, Executors.newFixedThreadPool(3),
                object : ImageCapture.OnImageSavedListener {
                    override fun onError(
                        imageCaptureError: ImageCapture.ImageCaptureError,
                        message: String,
                        exc: Throwable?
                    ) {
                        val msg = "Photo capture failed: $message"
                        Log.e("CameraXApp", msg, exc)
                        camera_preview.post {
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onImageSaved(file: File) {
                        //TODO: Navigate
                        val msg = "Photo capture succeeded: ${file.absolutePath}"
                        Log.d("CameraXApp", msg)
                        camera_preview.post {
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        // Bind use cases to lifecycle
        // If Android Studio complains about "this" being not a LifecycleOwner
        // try rebuilding the project or updating the appcompat dependency to
        // version 1.1.0 or higher.
        CameraX.bindToLifecycle(this, createPreviewUseCase(), imageCapture)
    }

    private fun createPreviewUseCase(): Preview {

        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640, 480))
        }.build()


        // Build the viewfinder use case
        val preview = Preview(previewConfig)

        // Every time the viewfinder is updated, recompute layout
        preview.setOnPreviewOutputUpdateListener {

            // To update the SurfaceTexture, we have to remove it and re-add it
            val parent = camera_preview.parent as ViewGroup
            parent.removeView(camera_preview)
            parent.addView(camera_preview, 0)

            camera_preview.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        return preview
    }
//
private fun updateTransform() {
    val matrix = Matrix()

    val centerX = camera_preview.width / 2f
    val centerY = camera_preview.height / 2f

    val rotationDegrees = when (findViewById<TextureView>(R.id.camera_preview).display.rotation) {
        Surface.ROTATION_0 -> 0
        Surface.ROTATION_90 -> 90
        Surface.ROTATION_180 -> 180
        Surface.ROTATION_270 -> 270
        else -> return
    }
    matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

    camera_preview.setTransform(matrix)
}



    companion object {
        fun startIntent(context: Context) = Intent(context, CameraActivity::class.java)
    }
}