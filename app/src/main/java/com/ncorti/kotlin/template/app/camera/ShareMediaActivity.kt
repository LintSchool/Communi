package com.ncorti.kotlin.template.app.camera

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncorti.kotlin.template.app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_share_media.*

class ShareMediaActivity : AppCompatActivity() {

    val capturedAdapter = CapturedMediaAdapter()
    var newImage : CapturedImage? = null
    var imagesList = mutableListOf<Any>()

    companion object {
        private val IMAGE_ACTIVITY_REQUEST_CODE = 0
        var NEW_IMAGE = "new image"

        fun buildIntent(context: Context, imagePath: String): Intent {
            val intent = Intent(context, ShareMediaActivity::class.java)
            intent.putExtra(NEW_IMAGE, imagePath)
            return intent

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_share_media)
        setUp()
    }

    fun setUp() {

        capturedAdapter.onAddClicked = {
            startActivityForResult(CameraActivity.startIntent(this), IMAGE_ACTIVITY_REQUEST_CODE)
        }

        captured_images_rv.adapter = capturedAdapter
        captured_images_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        if (intent.getStringExtra(NEW_IMAGE) != null) {
            newImage = CapturedImage(
                2,
                intent.getStringExtra(NEW_IMAGE)!!
            )
        }

        imagesList.add(0, newImage!!)
        imagesList.add("Placeholder for view type add")


        capturedAdapter.submitList(imagesList)


        if (newImage?.imagePath?.isNotEmpty() == true) {
            Picasso.get().load("file//: ${newImage?.imagePath}")
        }

        capturedAdapter.onItemClick = { position ->
            if ((capturedAdapter.currentList[position] as? CapturedImage)?.imagePath != "")
                Picasso.get()
                    .load((capturedAdapter.currentList[position] as? CapturedImage)?.imagePath)
                    .into(selected_photo_iv)
        }

        capturedAdapter.onItemRemove = {
            imagesList.removeAt(it)
            capturedAdapter.notifyItemRemoved(it)
        }

        back_iv.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    imagesList.add(
                        0, CapturedImage(
                            1,
                            data.getStringExtra(CameraActivity.CAPTURED_IMAGE)!!
                        )
                    )
                    capturedAdapter.notifyItemInserted(0)

                    Picasso.get()
                        .load(data.getStringExtra(CameraActivity.CAPTURED_IMAGE))
                        .into(selected_photo_iv)
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}