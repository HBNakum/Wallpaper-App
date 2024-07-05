package com.example.wall_app

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wall_app.databinding.ActivityFinalWallpaperBinding
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.Objects
import kotlin.random.Random

class FinalWallpaper : AppCompatActivity() {

    lateinit var binding: ActivityFinalWallpaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFinalWallpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url=intent.getStringExtra("link")
        val urlimage = URL(url)


        binding.finalWallpaper
        Glide.with(this).load(url).into(binding.finalWallpaper)

        binding.btnSetWallpaper.setOnClickListener {
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlimage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main){
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())
            }
        }

        binding.btnDownload.setOnClickListener {
            val result:Deferred<Bitmap?> = GlobalScope.async {
                urlimage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main){
                saveimage(result.await())
            }
        }
    }
    fun URL.toBitmap():Bitmap?{
        return  try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }

    private fun saveimage(image: Bitmap?){
        val random1 = Random.nextInt(520985)
        val random2 = Random.nextInt(520985)

        val name = "HYPER-${random1 + random2}"

        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,"$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES + File.separator + "Hyper Wallpaper")
            val imageuri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageuri)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG,100,data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image Save", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()
        }

    }
}