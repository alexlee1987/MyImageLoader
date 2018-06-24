package com.lgd.myimageloader

import java.io.Closeable
import java.io.IOException

/**
 * 封装的关闭对象方法的类
 * Created by Administrator on 2018/6/24 0024.
 */
object CloseUtils {
    /**
     * 关闭closeable对象
     * @param closeable
     */
    fun closeQuietly(closeable: Closeable?) {
        if(null != closeable) {
            try {
                closeable.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}