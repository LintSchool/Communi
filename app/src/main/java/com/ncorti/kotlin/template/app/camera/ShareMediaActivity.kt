package com.ncorti.kotlin.template.app.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncorti.kotlin.template.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_share_media.*
import java.io.File

class ShareMediaActivity : AppCompatActivity() {

    lateinit var capturedAdapter: CapturedMediaAdapter
    var newImage = CapturedImages(0, "")

    companion object {
        private val IMAGE_ACTIVITY_REQUEST_CODE = 0
        var NEW_IMAGE = "new image"
        fun buildIntent(context: Context, imagePath: String): Intent {
            var intent = Intent(context, ShareMediaActivity::class.java)
            intent.putExtra(NEW_IMAGE, imagePath)
            return intent

        }

        var imagesList = mutableListOf<CapturedImages>()
        var firstOpen = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_share_media)
        setUp()
    }

    fun setUp() {
        if (intent.getStringExtra(CameraActivity.CAPTURED_IMAGE) != null) {
            newImage = CapturedImages(
                2,
                intent.getStringExtra(CameraActivity.CAPTURED_IMAGE)!!
            )
        }
        imagesList.add(0, newImage)
        capturedAdapter = CapturedMediaAdapter()
        capturedAdapter.setData(imagesList)
        captured_images_rv.adapter = capturedAdapter
        captured_images_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        capturedAdapter.notifyDataSetChanged()

        if (newImage.imagePath != "") {
            Picasso.get()
                .load(newImage.imagePath)
                .into(selected_photo_iv)
        }

        capturedAdapter.onItemClick = { position ->
            if (capturedAdapter.imagesList[position].imagePath != "")
                Picasso.get().load(capturedAdapter.imagesList[position].imagePath).into(
                    selected_photo_iv
                )
        }

        capturedAdapter.onItemRemove = { position ->
            capturedAdapter.imagesList.removeAt(position)
            capturedAdapter.notifyItemRemoved(position)
        }

        openCameraBtn.setOnClickListener {
            startActivityForResult(CameraActivity.startIntent(this), IMAGE_ACTIVITY_REQUEST_CODE)
            firstOpen = false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    imagesList.add(0, CapturedImages(
                        1,
                        data.getStringExtra(CameraActivity.CAPTURED_IMAGE)!!
                    ))
                    capturedAdapter.setData(imagesList)
                    capturedAdapter.notifyDataSetChanged()

                    Picasso.get()
                        .load(data.getStringExtra(CameraActivity.CAPTURED_IMAGE))
                        .into(selected_photo_iv)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivityForResult(CameraActivity.startIntent(this), IMAGE_ACTIVITY_REQUEST_CODE)
        firstOpen = true
    }
}