package com.lgd.myimageloader

import android.graphics.Bitmap

/**
 * Created by Administrator on 2018/6/24 0024.
 */
interface ImageCache {
    fun get(url: String): Bitmap?
    fun put(url: String, bmp: Bitmap)
}