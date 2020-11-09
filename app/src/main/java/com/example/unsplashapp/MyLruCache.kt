package com.example.unsplashapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class MyLruCache(val lruCache: LruCache<String, Bitmap>){

    fun getBitmapFromCache(key: String): Bitmap? {
        if (lruCache.get(key) == null) {
            val bitmap = getBitmapFromURL(key)
            return bitmap
        } else {
            return lruCache.get(key)
        }
    }

    fun setBitmapToCache(key: String?, bitmap: Bitmap?) {
        if (getBitmapFromCache(key!!) == null) {
            lruCache.put(key, bitmap)
        }
    }

    fun getBitmapFromURL(src: String?): Bitmap? {
        var bitmap: Bitmap? = null
        val urlImage: URL = URL(src)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main) {
            bitmap = result.await()
            bitmap.apply {
                setBitmapToCache(src, bitmap)
            }
        }

        return bitmap
    }

    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }

}
