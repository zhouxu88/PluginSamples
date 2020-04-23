package com.zx.baseplugin

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * FileName：IPluginActivity
 * Create By：liumengqiang
 * Description：插件基类接口
 */
interface IPluginActivity {
    /**
     * 给插件Activity指定上下文
     *
     * @param activity
     */
    fun attach(activity: Activity?)

    // 以下全都是Activity生命周期函数,
    // 插件Activity本身 在被用作"插件"的时候不具备生命周期，由宿主里面的代理Activity类代为管理
    fun onCreate(saveInstanceState: Bundle?)
    fun onStart()
    fun onResume()
    fun onRestart()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

}