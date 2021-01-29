package com.zhf.player

import com.zhf.base.activity.IBaseView
import com.zhf.common.contract.BaseCustomViewModel
import java.util.*

/**
 * created by demoless on 2021/1/29
 * description:
 */
interface IVideoPlayerView: IBaseView {
    /**
     * 数据加载完成
     *
     * @param viewModels data
     */
    fun onDataLoadFinish(viewModels: ArrayList<BaseCustomViewModel?>?)
}