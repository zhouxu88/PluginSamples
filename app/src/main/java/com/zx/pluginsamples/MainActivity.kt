package com.zx.pluginsamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zx.plugin_core.PluginConst
import com.zx.plugin_core.PluginManager
import com.zx.plugin_core.ProxyActivity

/**
 * 插件化中的宿主apk
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loadPlugin1(view: View) {
        val apkPath = filesDir.path + PluginConst.plugin1_apk_path
        val activityName = "com.zx.plugin1.MainActivity"

        PluginManager.startActivity(this,apkPath,activityName)
    }

    fun loadPluginResource(view: View) {
        startActivity(Intent(this,TestLoadDynamicResourceActivity::class.java))
    }
}
