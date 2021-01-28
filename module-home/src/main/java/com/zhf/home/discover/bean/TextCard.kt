package com.zhf.home.discover.bean

import com.google.gson.annotations.SerializedName

/**
 * created by demoless on 2021/1/28
 * description:
 */
data class TextCard(@SerializedName("type")     val type: String,
               @SerializedName("data")     val data: DataBean,
               @SerializedName("tag")      val tag: Any,
               @SerializedName("id")       val id: Int,
               @SerializedName("adIndex")  val adIndex: Int) {

    /**
     * dataType : TextCardWithRightAndLeftTitle
     * id : 0
     * type : header8
     * text : 本周榜单
     * subTitle : null
     * actionUrl : eyepetizer://ranklist/
     * adTrack : null
     * follow : null
     * rightText : 查看全部
     */
    data class DataBean(@SerializedName("dataType")     val dataType: String,
                        @SerializedName("id")           val id: Int,
                        @SerializedName("type")         val type: String,
                        @SerializedName("text")         val text: String,
                        @SerializedName("subTitle")     val subTitle: Any,
                        @SerializedName("actionUrl")    val actionUrl: String,
                        @SerializedName("adTrack")      val adTrack: Any,
                        @SerializedName("follow")       val follow: Any,
                        @SerializedName("rightText")    val rightText: String)
}