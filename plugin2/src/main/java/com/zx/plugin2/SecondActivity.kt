package com.zx.plugin2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zx.plugin_core.PluginBaseActivity

class SecondActivity : PluginBaseActivity() {

    val TAG="SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
          Log.e(TAG, "this is plugin SecondActivity onCreate: ")
    }
}
