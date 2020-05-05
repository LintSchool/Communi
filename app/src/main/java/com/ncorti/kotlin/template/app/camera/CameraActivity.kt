package com.ncorti.kotlin.template.app.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
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
    val OPEN_SETTINGS_REQUEST_CODE = 102

    private var lensFacing = CameraX.LensFacing.BACK
    private var imageCapture: ImageCapture? = null


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
                    startActivityForResult(intent, OPEN_SETTINGS_REQUEST_CODE)
                })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == OPEN_SETTINGS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            camera_preview.post { startCamera() }
        }
    }

    private fun startCamera() {

        val preview = createPreviewUseCase()


        preview.setOnPreviewOutputUpdateListener {

            val parent = camera_preview.parent as ViewGroup
            parent.removeView(camera_preview)
            parent.addView(camera_preview, 0)

            camera_preview.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        imageCapture = createImageCapture()

        capture_btn.setOnClickListener {
            val file = File(
                externalMediaDirs.first(),
                "${System.currentTimeMillis()}.jpg"
            )

            (imageCapture as ImageCapture).takePicture(file, Executors.newSingleThreadExecutor(),
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
                        if(ShareMediaActivity.firstOpen) {
                            startActivity(ShareMediaActivity.buildIntent(this@CameraActivity, file.absolutePath))
                            finish()
                        }
                        else {
                            var intent = Intent()
                            var data = intent.putExtra(CAPTURED_IMAGE, file.absolutePath)
                            setResult(Activity.RESULT_OK, data)
                            finish()
                        }
                        val msg = "Photo capture succeeded: ${file.absolutePath}"
                        Log.d("CameraXApp", msg)
                        camera_preview.post {
                            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun createImageCapture(): ImageCapture {
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setLensFacing(lensFacing)
                setTargetRotation(camera_preview.display.rotation)
                setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
            }.build()

        // Build the image capture use case and attach button click listener
        return ImageCapture(imageCaptureConfig)
    }

    private fun createPreviewUseCase(): Preview {

        val previewConfig = PreviewConfig.Builder().apply {
            setLensFacing(lensFacing)
            setTargetRotation(camera_preview.display.rotation)
        }.build()

        return Preview(previewConfig)
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = camera_preview.width / 2f
        val centerY = camera_preview.height / 2f

        val rotationDegrees =
            when (camera_preview.display.rotation) {
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
        var CAPTURED_IMAGE = "captured_image"
    }
}