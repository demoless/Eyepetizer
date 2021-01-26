package com.zhf.main.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.zhf.base.storage.MmkvHelper
import com.zhf.common.adapter.ScreenAutoAdapter
import com.zhf.main.R

/**
 * created by demoless on 2021/1/26
 * description:
 */
class SplashActivity : AppCompatActivity() {
    private val mHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenAutoAdapter.match(this, 375.0f)
        setContentView(R.layout.main_activity_splash)
        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.top_view))
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .init()
        mHandler.postDelayed({ this.startToMain() }, 3000)
    }

    private fun startToMain() {
        if (MmkvHelper.getInstance().mmkv.decodeBool("first", true)) {
            startActivity(Intent(this, GuideActivity::class.java))
        } else {
            MainActivity.start(this)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        //activity销毁时移除所有消息,防止内存泄漏
        mHandler.removeCallbacksAndMessages(null)
    }
}