package com.zx.baseplugin

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexClassLoader
import java.io.File

class PluginProxyActivity : AppCompatActivity() {

    companion object {
        const val PLUGIN_APK_PATH = "PLUGIN_APK_PATH"
        const val PLUGIN_ACTIVITY_NAME = "PLUGIN_ACTIVITY_NAME"
    }

    private lateinit var dexClassLoader: DexClassLoader
    private  var resources: Resources?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRealActivity(savedInstanceState)
    }

    private fun loadRealActivity(savedInstanceState: Bundle?) {
        var pluginApkPath = intent.getStringExtra(PLUGIN_APK_PATH)
        var pluginActivityName = intent.getStringExtra(PLUGIN_ACTIVITY_NAME)

        loadPluginApk(pluginApkPath)
        var clazz = dexClassLoader.loadClass(pluginActivityName)
        var realActivity = clazz.newInstance()
        if (realActivity is IPluginActivity) {
            val bundle = Bundle()
            //设置是插件跳转
            bundle.putBoolean(PluginConst.isPlugin, true)
            realActivity.attach(this)
            realActivity.onCreate(bundle)
        }
    }

    /**
     * 加载插件APK
     * @param apkPath APK或者jar或者dex的目录
     */
    fun loadPluginApk(apkPath: String?) {
        //Dex优化后的缓存目录
        val odexFile: File = getDir("odex", Context.MODE_PRIVATE)
        //创建DexClassLoader加载器
        dexClassLoader =
            DexClassLoader(apkPath, odexFile.absolutePath, null, classLoader)
        //创建AssetManager，然后创建Resources
        try {
            val assetManager = AssetManager::class.java.newInstance()
            val method =
                AssetManager::class.java.getDeclaredMethod(
                    "addAssetPath",
                    String::class.java
                )
            method.invoke(assetManager, apkPath)
            resources = Resources(
                assetManager,
                getResources().displayMetrics,
                getResources().configuration
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getResources(): Resources {
//        return super.getResources()
        return (if (resources == null) super.getResources() else resources)!!
    }
}
