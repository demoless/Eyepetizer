package com.zhf.home.discover.bean

import com.google.gson.annotations.SerializedName

/**
 * created by demoless on 2021/1/28
 * description:
 */
class SquareCard(@SerializedName("dataType")    val dataType: String,
                 @SerializedName("id")          val id: Int,
                 @SerializedName("title")       val title: String,
                 @SerializedName("image")       val image: String,
                 @SerializedName("actionUrl")   val actionUrl: String,
                 @SerializedName("shade")       val shade: Boolean,
                 @SerializedName("adTrack")     val adTrack: Any,
                 @SerializedName("description") val description: String,) {
    /**
     * dataType : SquareCard
     * id : 14
     * title : #广告
     * image : http://img.kaiyanapp.com/57472e13fd2b6c9655c8a600597daf4d.png?imageMogr2/quality/60/format/jpg
     * actionUrl : eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A
     * shade : true
     * adTrack : null
     * description : null
     */
}