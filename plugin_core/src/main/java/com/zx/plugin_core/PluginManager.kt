package com.zx.plugin_core

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.content.res.Resources
import dalvik.system.DexClassLoader
import java.io.File

/**
 * @descripe
 *
 * @author zhouxu
 * @e-mail 374952705@qq.com
 * @time   2020/4/20
 */


object PluginManager {

    private var dexClassLoader: DexClassLoader? = null
    private var resources: Resources? = null

    /**
     * 加载外部插件apk
     *
     * @param context
     * @param apkPath
     */
    fun loadPluginApk(context: Context, apkPath: String) {
        var odex: File = context.getDir("odex", Context.MODE_PRIVATE)
        dexClassLoader = DexClassLoader(apkPath, odex.path, null, context.classLoader)
        //加载apk中的资源
        var assetManager: AssetManager = AssetManager::class.java.newInstance()
        val method =
            AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
        method.isAccessible=true
        method.invoke(assetManager, apkPath)
        resources = Resources(
            assetManager,
            context.resources.displayMetrics,
            context.resources.configuration
        )
    }

    fun getDexClassLoader(): DexClassLoader? = dexClassLoader


    fun getResources(): Resources? = resources

    /**
     * 跳转到插件中的Activity，实际上是跳转到代理Activity
     * @param context
     * @param apkPath
     * @param activityName
     */
    fun startActivity(context: Context,apkPath:String,activityName:String){
        var intent = Intent(context, ProxyActivity::class.java)
        intent.putExtra(ProxyActivity.PLUGIN_APK_PATH, apkPath)
        intent.putExtra(ProxyActivity.PLUGIN_ACTIVITY_NAME, activityName)
        context.startActivity(intent)
    }

}