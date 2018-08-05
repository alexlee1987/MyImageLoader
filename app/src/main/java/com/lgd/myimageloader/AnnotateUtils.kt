package com.lgd.myimageloader

import android.app.Activity
import java.lang.reflect.InvocationTargetException


/**
 * Created by Administrator on 2018/6/24 0024.
 */
object AnnotateUtils {
    fun injectViews(activity: Activity) {
        val `object` = activity.javaClass // 获取activity的Class
        val fields = `object`.declaredFields // 通过Class获取activity的所有字段
        for (field in fields) { // 遍历所有字段
            // 获取字段的注解，如果没有ViewInject注解，则返回null
            val viewInject = field.getAnnotation(ViewInject::class.java)
            if (viewInject != null) {
                val viewId = viewInject!!.value // 获取字段注解的参数，这就是我们传进去控件Id
                if (viewId != -1) {
                    try {
                        // 获取类中的findViewById方法，参数为int
                        val method = `object`.getMethod("findViewById", Int::class.javaPrimitiveType)
                        // 执行该方法，返回一个Object类型的View实例
                        val resView = method.invoke(activity, viewId)
                        field.isAccessible = true
                        // 把字段的值设置为该View的实例
                        field.set(activity, resView)
                    } catch (e: NoSuchMethodException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    } catch (e: InvocationTargetException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}