package com.lgd.myimageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileNotFoundException
import java.io.FileOutputStream

/**
 * 磁盘SD卡缓存策略：将图片缓存到SD卡
 * Created by Administrator on 2018/6/24 0024.
 */
class DiskCache: ImageCache {
    val cacheDir: String = "sdcard/cache"
    override fun get(url: String): Bitmap? {
        return BitmapFactory.decodeFile(cacheDir + url)//To change body of created functions use File | Settings | File Templates.
    }

    override fun put(url: String, bmp: Bitmap) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(cacheDir + url)
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: FileNotFoundException){
        } finally {
            CloseUtils.closeQuietly(fileOutputStream)
        }
    }
}