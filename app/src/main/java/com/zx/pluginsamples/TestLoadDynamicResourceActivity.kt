package com.zx.pluginsamples

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_load_dynamic_resource.*
import java.io.File
import java.lang.Exception
import java.lang.reflect.Method

/**
 * 测试加载外部apk资源（res目录那些）
 */
class TestLoadDynamicResourceActivity : AppCompatActivity() {

    private var apkPath: String? = null
    private var mResource: Resources? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_dynamic_resource)

        loadPluginResource()
        val context:Context=this
    }

    /**
     * 加载外部资源，这里是为了演示方便，直接加载了反射插件中指定方法，
     * 这里主要讲的是通过AssetManager、Resources去加载指定目录的资源，
     * 实际上插件化和热修复比这个要复杂一些，但是有些原理是相同的
     */
    private fun loadPluginResource() {
        try {
            apkPath = filesDir.path + "/dynamic_resource-debug.apk"
            initResources()

            var odexFile: File = getDir("odex", Context.MODE_PRIVATE)
            var dexClassLoader = DexClassLoader(apkPath, odexFile.path, null, classLoader)
            //加载插件中的资源获取工具类
            var clazz = dexClassLoader.loadClass("com.zx.dynamic_resource.ResourceUtils")
            var obj = clazz.newInstance()

            //加载插件里类中定义的字符串资源
            var method: Method = clazz.getMethod("getTextFromPlugin")
            var text: String = method.invoke(obj) as String
            tv1.text = text

            method = clazz.getMethod("getTextFromPluginRes", Context::class.java)
            text = method.invoke(obj, this) as String
            tv2.text = text

            method = clazz.getMethod("getDrawableFromPlugin", Context::class.java)
            var drawable: Drawable = method.invoke(obj, this) as Drawable
            image.setImageDrawable(drawable)
        } catch (e: Exception) {
            Log.e("tag", "loadPluginResource---: $e")
        }
    }

    /**
     * 加载外部apk中的资源，方法是通过反射调用AssetManager.addAssetPath()方法去
     * 让AssetManager加载指定路径的资源
     */
    private fun initResources() {
        var clazz = AssetManager::class.java
        var assetManager = clazz.newInstance()
        var addAssetPathMethod = clazz.getMethod("addAssetPath", String::class.java)
        addAssetPathMethod.invoke(assetManager, apkPath)

        mResource = Resources(assetManager, resources?.displayMetrics, resources?.configuration)
    }

    /**
     * 重写宿主中的Resources对象，这样就可以加载指定路径（外部apk）中的资源对象
     */
    override fun getResources(): Resources? {
        return if (mResource == null) super.getResources() else mResource
    }
}
