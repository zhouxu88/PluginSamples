package com.zx.plugin1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.zx.plugin_core.PluginBaseActivity
import com.zx.plugin_core.PluginConst

class MainActivity : PluginBaseActivity() {

    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "this is plugin1 MainActivity onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.to_second_btn)?.setOnClickListener {
            val apkPath = proxyActivity?.filesDir?.path + PluginConst.plugin1_apk_path
            startActivity(Intent(proxyActivity, SecondActivity::class.java), apkPath)
        }

        findViewById<Button>(R.id.to_plugin2_btn)?.setOnClickListener {
            val apkPath = proxyActivity?.filesDir?.path + PluginConst.plugin2_apk_path
            val activityName = "com.zx.plugin2.MainActivity"
            startOtherPluginActivity(apkPath, activityName)
        }
    }

}
