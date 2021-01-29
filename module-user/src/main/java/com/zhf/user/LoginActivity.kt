package com.zhf.user

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhf.common.router.RouterActivityPath
import com.zhf.common.services.ILoginService
import com.zhf.common.services.config.ServicesConfig
import com.zhf.user.databinding.UserActivityLoginBinding

/**
 * created by demoless on 2021/1/29
 * description:
 */
@Route(path = RouterActivityPath.User.PAGER_LOGIN)
class LoginActivity : AppCompatActivity() {

    @Autowired(name = ServicesConfig.User.LONGING_SERVICE)
    var iLoginService: ILoginService? = null

    private val animatorSet by lazy {
        AnimatorSet().also {
            it.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    // 循环播放
                    animation.start()
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
    }

    private val binding: UserActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.user_activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initView()
        initData()
    }

    private fun initData() {
        //模拟登录
        iLoginService?.saveStatus(false)
    }

    private fun initView() {
        val animator1 = ObjectAnimator.ofFloat(binding.loginBgImage1, "alpha", 1.0f, 0f)
        val animator2 = ObjectAnimator.ofFloat(binding.loginBgImage2, "alpha", 0f, 1.0f)
        val animatorScale1 = ObjectAnimator.ofFloat(binding.loginBgImage1, "scaleX", 1.0f, 1.3f)
        val animatorScale2 = ObjectAnimator.ofFloat(binding.loginBgImage1, "scaleY", 1.0f, 1.3f)
        val animatorSet1 = AnimatorSet()
        animatorSet1.duration = 5000
        animatorSet1.play(animator1).with(animator2).with(animatorScale1).with(animatorScale2)
        animatorSet1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                // 放大的View复位
                binding.loginBgImage1.scaleX = 1.0f
                binding.loginBgImage1.scaleY = 1.0f
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        val animator3 = ObjectAnimator.ofFloat(binding.loginBgImage2, "alpha", 1.0f, 0f)
        val animator4 = ObjectAnimator.ofFloat(binding.loginBgImage1, "alpha", 0f, 1.0f)
        val animatorScale3 = ObjectAnimator.ofFloat(binding.loginBgImage2, "scaleX", 1.0f, 1.3f)
        val animatorScale4 = ObjectAnimator.ofFloat(binding.loginBgImage2, "scaleY", 1.0f, 1.3f)
        val animatorSet2 = AnimatorSet()
        animatorSet2.duration = 5000
        animatorSet2.play(animator3).with(animator4).with(animatorScale3).with(animatorScale4)
        animatorSet2.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                // 放大的View复位
                binding.loginBgImage2.scaleX = 1.0f
                binding.loginBgImage2.scaleY = 1.0f
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animatorSet.playSequentially(animatorSet1, animatorSet2)

        animatorSet.start()
    }

    override fun onPause() {
        super.onPause()
        animatorSet.cancel()
    }

}