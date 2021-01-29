package com.zhf.player

import com.zhf.base.model.BaseModel
import com.zhf.base.utils.GsonUtils
import com.zhf.common.contract.BaseCustomViewModel
import com.zhf.http.EasyHttp
import com.zhf.http.exception.ApiException
import com.zhf.http.subsciber.BaseSubscriber
import com.zhf.player.bean.LeftAlignTextHeader
import com.zhf.player.bean.ReplyBean
import com.zhf.player.bean.TextCard
import com.zhf.player.bean.VideoSmallCard
import com.zhf.player.bean.viewmodel.ReplyViewModel
import com.zhf.player.bean.viewmodel.VideoCardViewModel
import com.zhf.player.bean.viewmodel.VideoTextViewModel
import io.reactivex.Observable
import org.json.JSONException
import org.json.JSONObject
import java.util.*

/**
 * created by demoless on 2021/1/29
 * description:
 */
class VideoPlayerModel<T>: BaseModel<T>() {

    companion object {
        /**
         * 相关推荐
         */
        private const val NOMINATE_URL: String = "http://baobab.kaiyanapp.com/api/v4/video/related"

        /**
         * 热门评论
         */
        private const val REPLY_URL: String = "http://baobab.kaiyanapp.com/api/v2/replies/video"
    }

    var videoId = 186856

    override fun load() {
        val nominateObservable = EasyHttp.get(NOMINATE_URL)
                .params("id", videoId.toString())
                .cacheKey("nominate")
                .execute(String::class.java)
        val replyObservable = EasyHttp.get(REPLY_URL)
                .params("videoId", videoId.toString())
                .cacheKey("reply")
                .execute(String::class.java)
        // 使用zip操作符 合并网络请求 统一处理结果
        // 使用zip操作符 合并网络请求 统一处理结果
        Observable.zip(nominateObservable,
                replyObservable,
                { s, s2 -> parseJson(s, s2) })
                .subscribe(object : BaseSubscriber<ArrayList<BaseCustomViewModel>>() {
                    override fun onError(e: ApiException) {
                        loadFail(e.message)
                    }

                    override fun onNext(viewModels: ArrayList<BaseCustomViewModel>) {
                        loadSuccess(viewModels as T)
                    }
                })
    }

    private fun parseJson(nominateData: String,
                          replyData: String): ArrayList<BaseCustomViewModel> {
        val viewModels = ArrayList<BaseCustomViewModel>()
        parseNominateData(viewModels, nominateData)
        parseReplyData(viewModels, replyData)
        return viewModels
    }

    private fun parseNominateData(viewModels: ArrayList<BaseCustomViewModel>,
                                  nominateData: String) {
        try {
            val jsonObject = JSONObject(nominateData)
            val itemList = jsonObject.optJSONArray("itemList")
            if (itemList != null) {
                for (i in 0 until itemList.length()) {
                    val currentObject = itemList.getJSONObject(i)
                    when (currentObject.optString("type")) {
                        "textCard" -> {
                            val textCard = GsonUtils.fromLocalJson(currentObject.toString(), TextCard::class.java)
                            val textViewModel = VideoTextViewModel().apply {
                                this.textTitle = textCard.data.text
                            }
                            viewModels.add(textViewModel)
                        }
                        "videoSmallCard" -> {
                            val videoSmallCard = GsonUtils
                                    .fromLocalJson(currentObject.toString(),
                                            VideoSmallCard::class.java)
                            paresVideoCard(viewModels, videoSmallCard)
                        }
                        else -> {
                        }
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun parseReplyData(viewModels: ArrayList<BaseCustomViewModel>, replyData: String) {
        try {
            val jsonObject = JSONObject(replyData)
            val itemList = jsonObject.optJSONArray("itemList")
            if (itemList != null) {
                for (i in 0 until itemList.length()) {
                    val currentObject = itemList.getJSONObject(i)
                    when (currentObject.optString("type")) {
                        "leftAlignTextHeader" -> {
                            val alignTextHeader = GsonUtils
                                    .fromLocalJson(currentObject.toString(), LeftAlignTextHeader::class.java)
                            val textViewModel = VideoTextViewModel().apply { this.textTitle = alignTextHeader.data.text }
                            viewModels.add(textViewModel)
                        }
                        "reply" -> {
                            val reply = GsonUtils.fromLocalJson(currentObject.toString(), ReplyBean::class.java)
                            val replyViewModel = ReplyViewModel().also {
                                it.avatar = reply.data.user.avatar
                                it.nickName = reply.data.user.nickname
                                it.replyMessage = reply.data.message
                                it.releaseTime = reply.data.user.releaseDate
                                it.likeCount = reply.data.likeCount
                            }
                            viewModels.add(replyViewModel)
                        }
                        else -> {
                        }
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun paresVideoCard(viewModels: ArrayList<BaseCustomViewModel>,
                               videoSmallCard: VideoSmallCard?) {
        if (videoSmallCard == null) {
            return
        }
        val videoCardViewModel = VideoCardViewModel().also {
            it.coverUrl = videoSmallCard.data.cover.detail
            it.videoTime = videoSmallCard.data.duration
            it.title = videoSmallCard.data.title
            it.description = (videoSmallCard.data.author.name + " / # "
                    + videoSmallCard.data.category)
            it.authorUrl = videoSmallCard.data.author.icon
            it.userDescription = videoSmallCard.data.author.description
            it.nickName = videoSmallCard.data.author.name
            it.video_description = videoSmallCard.data.description
            it.playerUrl = videoSmallCard.data.playUrl
            it.blurredUrl = videoSmallCard.data.cover.blurred
            it.videoId = videoSmallCard.data.id
            it.collectionCount = videoSmallCard.data.consumption.collectionCount
            it.shareCount = videoSmallCard.data.consumption.shareCount
        }

        viewModels.add(videoCardViewModel)
    }
}