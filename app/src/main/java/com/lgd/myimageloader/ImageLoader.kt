package com.lgd.myimageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Administrator on 2018/6/24 0024.
 */
class ImageLoader {
    // 图片缓存
    var mImageCache: ImageCache = MemoryCache()
    // 线程池，线程数量为CPU的数量
    val mExecutorService: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    // 注入缓存实现
    fun setImageCache(cache: ImageCache) {
        mImageCache = cache
    }

    fun displayImage(url: String, imageView: ImageView) {
        val bitmap: Bitmap? = mImageCache.get(url)
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            return
        }
        // 图片没有缓存，提交到线程池中下载图片
        submitLoadRequest(url, imageView)
    }

    private fun submitLoadRequest(imageUrl: String, imageView: ImageView) {
        imageView.setTag(imageUrl)
        mExecutorService.submit( {
            var bitmap: Bitmap? = downloadImage(imageUrl) ?: return@submit
            if (imageView.getTag().equals(imageUrl)) {
                imageView.setImageBitmap(bitmap)
            }
            mImageCache.put(imageUrl, bitmap!!)
        })
    }

    private fun downloadImage(imageUrl: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            var url = URL(imageUrl)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}