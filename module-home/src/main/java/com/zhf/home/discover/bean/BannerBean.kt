package com.zhf.home.discover.bean

import com.google.gson.annotations.SerializedName

/**
 * created by demoless on 2021/1/28
 * description:
 * type : banner
 * data :
 * {"dataType":"Banner","id":1682,"title":"新年购物车：2020年手帐清单","description":null,"image":"http://img.kaiyanapp.com/80b385aa137e223421c92ee389c99e83.jpeg?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fnew_article.html%3Fnid%3D1468%26shareable%3Dtrue","adTrack":null,"shade":false,"label":null,"labelList":null,"header":null,"autoPlay":false}
 * tag : null id : 0 adIndex : -1
 */
data class BannerBean(@SerializedName("type")   val type: String,
                 @SerializedName("data")        val data: DataBean,
                 @SerializedName("tag")         val tag: Any,
                 @SerializedName("id")          val id: Int,
                 @SerializedName("adIndex")     val adIndex: Int) {

    /**
     * dataType : Banner
     * id : 1682
     * title : 新年购物车：2020年手帐清单
     * description :null
     * image : http://img.kaiyanapp.com/80b385aa137e223421c92ee389c99e83.jpeg?imageMogr2/quality/60/format/jpg
     * actionUrl : eyepetizer://webview/?title=&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fnew_article.html%3Fnid%3D1468%26shareable%3Dtrue
     * adTrack : null shade : false label : null labelList : null header : null autoPlay : false
     */
    data class DataBean(@SerializedName("dataType")     val dataType: String,
                   @SerializedName("id")                val id: Int,
                   @SerializedName("title")             val title: String,
                   @SerializedName("description")       val description: Any,
                   @SerializedName("image")             val image: String,
                   @SerializedName("actionUrl")         val actionUrl: String,
                   @SerializedName("adTrack")           val adTrack: Any,
                   @SerializedName("shade")             val shade: Boolean,
                   @SerializedName("label")             val label: Any,
                   @SerializedName("labelList")         val labelList: Any,
                   @SerializedName("header")            val header: Any,
                   @SerializedName("autoPlay")          val autoPlay:Boolean)
}