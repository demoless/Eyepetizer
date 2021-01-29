package com.zhf.player

import com.zhf.base.model.BaseModel
import com.zhf.base.model.IModelListener
import com.zhf.base.viewmodel.MvmBaseViewModel
import com.zhf.common.contract.BaseCustomViewModel

/**
 * created by demoless on 2021/1/29
 * description:
 */
class VideoPlayerViewModel: MvmBaseViewModel<IVideoPlayerView, VideoPlayerModel<*>>(),
        IModelListener<ArrayList<BaseCustomViewModel>> {

    override fun initModel(): VideoPlayerModel<*> {
        model = VideoPlayerModel<Any>()
        model.register(this)
        return model
    }

    fun loadData(videoId: Int) {
        model.videoId = videoId
        model.load()
    }

    override fun onLoadFinish(model: BaseModel<*>, data: ArrayList<BaseCustomViewModel>) {
        if (data.isNotEmpty()) {
            pageView?.onDataLoadFinish(data)
        } else {
            pageView?.showEmpty()
        }
    }

    override fun onLoadFail(model: BaseModel<*>, prompt: String) {
        pageView?.showFailure(prompt)
    }

    override fun detachUi() {
        super.detachUi()
        model.unRegister(this)
    }
}