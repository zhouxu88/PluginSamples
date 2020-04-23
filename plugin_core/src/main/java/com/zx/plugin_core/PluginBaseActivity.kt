package com.zx.plugin_core

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @descripe
 *
 * @author zhouxu
 * @e-mail 374952705@qq.com
 * @time   2020/4/20
 */


open class PluginBaseActivity : AppCompatActivity(), IPluginActivity {
    val Tag = "PluginBaseActivity"

    /**
     * 是否为插件模式
     */
    private var isPlugin: Boolean = false
    protected var proxyActivity: Activity? = null

    override fun attach(activity: Activity?) {
        proxyActivity = activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            isPlugin = savedInstanceState?.getBoolean(PluginConst.isPlugin, false)
        }
        if (!isPlugin) {
            super.onCreate(savedInstanceState)
            proxyActivity = this
        }
        Log.e(Tag, "onCreate isPlugin : $isPlugin")
    }

    override fun onStart() {
        if (!isPlugin) {
            super.onStart()
        }
        Log.e(Tag, "onStart: ")
    }

    override fun onRestart() {
        if (!isPlugin) {
            super.onRestart()
        }
        Log.e(Tag, "onRestart: ")
    }

    override fun onResume() {
        if (!isPlugin) {
            super.onResume()
        }
        Log.e(Tag, "onResume: ")
    }

    override fun onPause() {
        if (!isPlugin) {
            super.onPause()
        }
        Log.e(Tag, "onPause: ")
    }

    override fun onStop() {
        if (!isPlugin) {
            super.onStop()
        }
        Log.e(Tag, "onStop: ")
    }

    override fun onDestroy() {
        if (!isPlugin) {
            super.onDestroy()
        }
        Log.e(Tag, "onDestroy: ")
    }

    override fun setContentView(layoutResID: Int) {
        if (!isPlugin) {
            super.setContentView(layoutResID)
        } else {
            proxyActivity?.setContentView(layoutResID)
        }
    }

    override fun <T : View?> findViewById(id: Int): T? {
        return if (!isPlugin) {
            super.findViewById<T>(id)
        } else {
            proxyActivity?.findViewById<T>(id)
        }
    }

    /**
     * 同一个插件之间跳转
     * @param intent
     */
    fun startActivity(intent: Intent, apkPath: String) {
        if (!isPlugin) {
            super.startActivity(intent)
        } else {
            PluginManager.startActivity(proxyActivity!!, apkPath, intent.component!!.className)
        }
    }


    /**
     * 一个插件跳转到另一个插件Activity
     * @param apkPath
     * @param activityName
     */
    fun startOtherPluginActivity(apkPath: String, activityName: String) {
        if (!isPlugin) {
            return
        }
        PluginManager.startActivity(proxyActivity!!, apkPath, activityName)
    }


}