package com.zhf.home.discover.bean

import com.google.gson.annotations.SerializedName

/**
 * created by demoless on 2021/1/28
 * description:
 */
class BriefCard(@SerializedName("type")     val type: String,
                @SerializedName("data")     val data: DataBean,
                @SerializedName("tag")      val tag: Any,
                @SerializedName("id")       val id: Int,
                @SerializedName("adIndex")  val adIndex: Int) {
    /**
     * dataType : TagBriefCard
     * id : 930
     * icon : http://img.kaiyanapp.com/586d2cdc4a807eb84bed71fc523ed24b.jpeg?imageMogr2/quality/60/format/jpg
     * iconType : square
     * title : #摄影师日常
     * subTitle : null
     * description : 透过镜头，我看到了更加真实的世界
     * actionUrl : eyepetizer://tag/930/?title=%E6%91%84%E5%BD%B1%E5%B8%88%E6%97%A5%E5%B8%B8
     * adTrack : null
     * follow : {"itemType":"tag","itemId":930,"followed":false}
     * ifPgc : false
     * uid : 0
     * ifShowNotificationIcon : false
     * switchStatus : false
     * medalIcon : true
     * haveReward : false
     * ifNewest : false
     * newestEndTime : null
     * expert : false
     */
    data class DataBean(@SerializedName("dataType")     val dataType: String,
                        @SerializedName("id")           val id: Int,
                        @SerializedName("icon")         val icon: String,
                        @SerializedName("iconType")     val iconType: String,
                        @SerializedName("title")        val title: String,
                        @SerializedName("subTitle")     val subTitle: Any,
                        @SerializedName("description")  val description: String,
                        @SerializedName("actionUrl")    val actionUrl: String,
                        @SerializedName("adTrack")      val adTrack: Any,
                        @SerializedName("follow")       val follow: FollowBean,
                        @SerializedName("ifPgc")        val ifPgc: Boolean,
                        @SerializedName("uid")          val uid: Int,
                        @SerializedName("ifShowNotificationIcon")
                        val ifShowNotificationIcon: Boolean,
                        @SerializedName("switchStatus") val switchStatus: Boolean,
                        @SerializedName("medalIcon")    val medalIcon: Boolean,
                        @SerializedName("haveReward")   val haveReward: Boolean,
                        @SerializedName("ifNewest")     val ifNewest: Boolean,
                        @SerializedName("newestEndTime")
                        val newestEndTime: Any,
                        @SerializedName("expert")       val expert: Boolean,)

    /**
     * follow : {"itemType":"tag","itemId":930,"followed":false}
     */
    data class FollowBean(@SerializedName("itemType")   val itemType: String,
                          @SerializedName("itemId")     val itemId: Int,
                          @SerializedName("followed")   val followed: Boolean)
}