package com.zhf.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.zhf.base.fragment.MvvmBaseFragment
import com.zhf.base.viewmodel.IMvvmBaseViewModel
import com.zhf.common.router.RouterFragmentPath
import com.zhf.user.adapter.RecyclerAdapter
import com.zhf.user.databinding.UserFragmentLayoutBinding
import java.util.*

/**
 * created by demoless on 2021/1/29
 * description:
 */
@Route(path = RouterFragmentPath.User.PAGER_USER)
class UserFragment() : MvvmBaseFragment<UserFragmentLayoutBinding, IMvvmBaseViewModel<*>>() {

    private val adapter by lazy {
        RecyclerAdapter()
    }

    private fun initData() {
        val items: MutableList<String> = ArrayList()
        items.add("我的关注")
        items.add("我的收藏")
        items.add("视频功能声明")
        items.add("用户协议")
        items.add("版权声明")
        items.add("关于作者")
        adapter.setNewData(items)
    }

    private fun start(context: Context?) {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    private fun initView() {
        Glide.with(context!!).load(context!!.getDrawable(R.drawable.avatar))
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(viewDataBinding.ivAvatar)
        viewDataBinding.rvTables.setHasFixedSize(true)
        viewDataBinding.rvTables.layoutManager = LinearLayoutManager(context)
        adapter.setFooterView(getFooterView())
        viewDataBinding.rvTables.adapter = adapter
        viewDataBinding.ivMore.setOnClickListener { start(context) }
        viewDataBinding.tvLike.setOnClickListener { start(context) }
        viewDataBinding.tvReply.setOnClickListener { start(context) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun getFooterView(): View {
        return LayoutInflater.from(context).inflate(R.layout.user_item_footer_view, viewDataBinding.rvTables, false)
    }
    override fun getLayoutId(): Int = R.layout.user_fragment_layout

    override fun getBindingVariable(): Int = 0

    override fun getViewModel(): IMvvmBaseViewModel<*>? = null

    override fun onRetryBtnClick() {

    }
}