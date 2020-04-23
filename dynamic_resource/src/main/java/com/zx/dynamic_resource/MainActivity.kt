package com.zx.dynamic_resource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 测试加载外部apk资源（res目录那些）
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resourceUtils = ResourceUtils()
        tv1.text = resourceUtils.getTextFromPlugin()
        tv2.text = resourceUtils.getTextFromPluginRes(this)
        image.setImageDrawable(resourceUtils.getDrawableFromPlugin(this))
    }
}
