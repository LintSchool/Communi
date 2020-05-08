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
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.utils.createFile
import com.ncorti.kotlin.template.app.utils.showSnackbar
import java.io.File
import java.util.concurrent.Executors
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val CAMERA_PERMISSION = Manifest.permission.CAMERA
    val PERMISSIONS_REQUEST_CODE = 101
    val OPEN_SETTINGS_REQUEST_CODE = 102

    private var lensFacing = CameraX.LensFacing.BACK
    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera)

        setup()
    }

    private fun setup() {

        requestPermissions()

        capture_btn.setOnClickListener {
            capturePhoto()
        }

        close_iv.setOnClickListener {
            finish()
        }

        camera_toggle_iv.setOnClickListener {
            toggleCameraLens()
        }
    }

    private fun toggleCameraLens() {
        if(lensFacing == CameraX.LensFacing.BACK) {
            lensFacing = CameraX.LensFacing.FRONT
        } else {
            lensFacing = CameraX.LensFacing.BACK
        }

        camera_preview.post { startCamera() }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (allPermissionsGranted()) {
                camera_preview.post { startCamera() }
            } else {
                camera_activity_root.showSnackbar(
                    getString(R.string.camera_permission_snackbar_text),
                    getString(R.string.turn_on),
                    {
                        val uri = Uri.fromParts("package", packageName, null)
                        val intent = Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = uri
                        }
                        startActivityForResult(intent, OPEN_SETTINGS_REQUEST_CODE)
                    }
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == OPEN_SETTINGS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            camera_preview.post { startCamera() }
        }
    }

    private fun requestPermissions() {
        if (allPermissionsGranted()) {
            camera_preview.post { startCamera() }
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE
            )
        }
    }

    private fun startCamera() {

        CameraX.unbindAll()

        val preview = createPreviewUseCase()

        preview.setOnPreviewOutputUpdateListener {
            val parent = camera_preview.parent as ViewGroup
            parent.removeView(camera_preview)
            parent.addView(camera_preview, 0)

            camera_preview.surfaceTexture = it.surfaceTexture
            updateTransform()
        }

        imageCapture = createImageCapture()

        CameraX.bindToLifecycle(this, preview, imageCapture)
    }

    private fun createImageCapture(): ImageCapture {
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setLensFacing(lensFacing)
                setTargetRotation(camera_preview.display.rotation)
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
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

    private fun capturePhoto() {
        val file = createFile(this)

        imageCapture?.takePicture(
            file, Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageSavedListener {
                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String,
                    exc: Throwable?
                ) {
                    Log.d(this@CameraActivity::class.java.simpleName, "Error Capturing image ${message}")
                }

                override fun onImageSaved(file: File) {
                    val uri = FileProvider.getUriForFile(this@CameraActivity, "$packageName.provider", file)
                    callingActivity?.let {
                        val intent = Intent()
                        val data = intent.putExtra(CAPTURED_IMAGE, uri)
                        setResult(Activity.RESULT_OK, data)
                        finish()
                    } ?: run {
                        startActivity(
                            ShareMediaActivity.buildIntent(
                                this@CameraActivity,
                                uri
                            )
                        )
                    }
                }
            }
        )
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        fun startIntent(context: Context) = Intent(context, CameraActivity::class.java)
        var CAPTURED_IMAGE = "captured_image"
    }
}
