package com.zhf.user.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zhf.user.R

/**
 * created by demoless on 2021/1/29
 * description:
 */
class RecyclerAdapter() : BaseQuickAdapter<String, BaseViewHolder>(R.layout.user_item_view_layout) {
    override fun convert(baseViewHolder: BaseViewHolder, s: String?) {
        baseViewHolder.setText(R.id.tv_item, s)
    }
}