package com.zx.plugin1

import android.os.Bundle
import android.util.Log
import com.zx.plugin_core.PluginBaseActivity

class SecondActivity : PluginBaseActivity() {

    val TAG = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.e(TAG, "this is plugin2 SecondActivity onCreate: ")
    }
}
