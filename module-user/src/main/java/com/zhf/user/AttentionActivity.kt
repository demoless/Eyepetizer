package com.zhf.user

import android.animation.Animator
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhf.common.router.RouterActivityPath

/**
 * created by demoless on 2021/1/29
 * description: 关注页面
 */
@Route(path = RouterActivityPath.User.PAGER_ATTENTION)
class AttentionActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_attention)
    }
}