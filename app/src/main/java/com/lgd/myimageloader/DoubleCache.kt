package com.lgd.myimageloader

import android.graphics.Bitmap

/**
 * 双缓存策略
 * Created by Administrator on 2018/6/24 0024.
 */
class DoubleCache: ImageCache {
    val mMemoryCache: ImageCache = MemoryCache()
    val mDiskCache: ImageCache = DiskCache()
    override fun get(url: String): Bitmap? {
        var bitmap: Bitmap? = mMemoryCache.get(url)
        return bitmap ?: mDiskCache.get(url)
    }

    override fun put(url: String, bmp: Bitmap) {
        mMemoryCache.put(url, bmp)
        mDiskCache.put(url, bmp)
    }
}