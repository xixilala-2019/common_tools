package com.xixilala.testphotogallery

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnOpenGallery).setOnClickListener { chooseAlbumPic() }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ALBUM ) {

            //拍照成功和选取照片时
            if (resultCode == Activity.RESULT_OK) {
                var imageUri: Uri? = null
                when (requestCode) {
                    REQUEST_CODE_ALBUM -> if (data != null) {
                        imageUri = data.data

                        val file = FileUtil.getFilePathByUri(this, imageUri)

                        Log.d("gallery123","------${imageUri.toString()}  fileName=$file  file=${file?.length}")

                        val bm = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri!!))
                        findViewById<ImageView>(R.id.imageView).setImageBitmap(bm)
                    }
                }
            }
        }
    }

    /**
     * 选择相册照片
     */
    private fun chooseAlbumPic() {
        val i =
            Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        startActivityForResult(
            Intent.createChooser(i, "Image Chooser"),
            REQUEST_CODE_ALBUM
        )
    }
    private val REQUEST_CODE_ALBUM = 0x01
}