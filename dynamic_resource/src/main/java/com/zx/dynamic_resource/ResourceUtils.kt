package com.zx.dynamic_resource

import android.content.Context

import android.graphics.drawable.Drawable


/**
 * @descripe 读取插件资源文件的工具类
 *
 * @author zhouxu
 * @e-mail 374952705@qq.com
 * @time   2020/4/21
 */


class ResourceUtils {
    //直接返回文本字符串
    fun getTextFromPlugin(): String {
        return "插件APK类里的文本内容"
    }

   //读取资源文件里的文本字符串
    fun getTextFromPluginRes(context: Context): String? {
        return context.resources.getString(R.string.plugin_text)
    }

    //读取资源文件里的图片
    fun getDrawableFromPlugin(context: Context): Drawable? {
        return context.resources.getDrawable(R.drawable.kb)
    }
}