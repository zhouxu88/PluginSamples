package com.zx.plugin2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.zx.plugin_core.PluginBaseActivity
import com.zx.plugin_core.PluginConst
import com.zx.plugin_core.ProxyActivity

/**
 * 插件2中的入口
 */
class MainActivity : PluginBaseActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "this is plugin MainActivity onCreate: ")

       findViewById<Button>(R.id.to_second_btn)?.setOnClickListener {
            jump()
        }
    }

    private fun jump() {
        val apkPath = proxyActivity?.filesDir?.path + PluginConst.plugin2_apk_path
//        val activityName = "com.zx.plugin2.SecondActivity"
//
//        var intent = Intent(proxyActivity, ProxyActivity::class.java)
//        intent.putExtra(ProxyActivity.PLUGIN_APK_PATH, apkPath)
//        intent.putExtra(ProxyActivity.PLUGIN_ACTIVITY_NAME, activityName)
//        proxyActivity?.startActivity(intent)

        startActivity(Intent(proxyActivity, SecondActivity::class.java),apkPath)
    }


}
