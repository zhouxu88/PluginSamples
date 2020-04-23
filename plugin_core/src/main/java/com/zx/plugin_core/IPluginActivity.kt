package com.zx.plugin_core

import android.app.Activity
import android.os.Bundle

/**
 * @descripe
 *
 * @author zhouxu
 * @e-mail 374952705@qq.com
 * @time   2020/4/20
 */


interface IPluginActivity {
    /**
     * 给插件Activity指定上下文
     *
     * @param activity
     */
    fun attach(activity: Activity?)

    // 插件Activity本身 在被用作"插件"的时候不具备生命周期，只能由宿主里面的代理Activity传递
    fun onCreate(saveInstanceState: Bundle?)
    fun onStart()
    fun onResume()
    fun onRestart()
    fun onPause()
    fun onStop()
    fun onDestroy()
}