package com.lgd.myimageloader

import android.graphics.Bitmap
import android.util.LruCache

/**
 * 内存缓存策略：将图片缓存到内存中
 * Created by Administrator on 2018/6/24 0024.
 */
class MemoryCache: ImageCache {
    private var mMemoryCache: LruCache<String, Bitmap>? = null
    init {
        val maxMemory: Int = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize: Int = maxMemory / 4
        mMemoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                return bitmap.rowBytes * bitmap.height / 1024
            }
        }
    }
    override fun get(url: String): Bitmap? {
        return mMemoryCache?.get(url) //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(url: String, bmp: Bitmap) {
        mMemoryCache?.put(url, bmp) //To change body of created functions use File | Settings | File Templates.
    }
}