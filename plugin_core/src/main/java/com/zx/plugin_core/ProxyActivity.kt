package com.zx.plugin_core

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexClassLoader
import java.io.File

/**
 * 插件化的代理Activity
 */
class ProxyActivity : AppCompatActivity() {

    companion object {
        const val PLUGIN_APK_PATH = "PLUGIN_APK_PATH"
        const val PLUGIN_ACTIVITY_NAME = "PLUGIN_ACTIVITY_NAME"
    }

    private var iPluginActivity: IPluginActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRealActivity()
    }

    /**
     * 加载插件中真正的Activity，通常是加载插件apk入口Activity
     */
    private fun loadRealActivity() {
        var pluginApkPath = intent.getStringExtra(PLUGIN_APK_PATH)
        var pluginActivityName = intent.getStringExtra(PLUGIN_ACTIVITY_NAME)
        PluginManager.loadPluginApk(this, pluginApkPath)
        var clazz = PluginManager.getDexClassLoader()?.loadClass(pluginActivityName)
        var realActivity = clazz?.newInstance()
        /**
         * 把生命周期相关方法通过接口传递给插件Activity，
         * 为了让插件可以加载资源，需要将上下文对象Context也传递过去
         */
        if (realActivity is IPluginActivity) {
            iPluginActivity = realActivity
            val bundle = Bundle()
            //设置是插件跳转
            bundle.putBoolean(PluginConst.isPlugin, true)
            realActivity.attach(this)
            realActivity.onCreate(bundle)
        }
    }

    override fun onStart() {
        iPluginActivity?.onStart()
        super.onStart()
    }

    override fun onRestart() {
        iPluginActivity?.onRestart()
        super.onRestart()
    }

    override fun onResume() {
        iPluginActivity?.onResume()
        super.onResume()
    }

    override fun onPause() {
        iPluginActivity?.onPause()
        super.onPause()
    }

    override fun onStop() {
        iPluginActivity?.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        iPluginActivity?.onDestroy()
        super.onDestroy()
    }


    override fun getResources(): Resources? {
        return if (PluginManager.getResources() == null) super.getResources() else PluginManager.getResources()
    }
}
