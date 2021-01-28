package com.zhf.home.discover.bean

import com.google.gson.annotations.SerializedName

/**
 * created by demoless on 2021/1/28
 * description: 顶部banner
 */
data class TopBanner (@SerializedName("type")       val type: String,
                      @SerializedName("data")       val data: DataBean,
                      @SerializedName("tag")        val tag: Any,
                      @SerializedName("id")         val id: Int,
                      @SerializedName("adIndex")    val adIndex: Int,) {
    /**
     * type : horizontalScrollCard
     * data : {"dataType":"HorizontalScrollCard","itemList":[{"type":"banner",
     * "data":{
     * "dataType":"Banner","id":1691,"title":"开眼请你看电影","description":"","image":"http://img.kaiyanapp.com/1eaf8827688ea3b910b7b6b6cb192a5f.png?imageMogr2/quality/60/format/jpg",
     * "actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E4%B8%80%E5%8F%B7%E6%94%BE%E6%98%A0%E5%8E%85&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fnew_article.html%3Fnid%3D1516%26shareable%3Dtrue%26type%3DarticleTopic%26cid%3D1691",
     * "adTrack":[],"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false},"tag":null,"id":0,"adIndex":-1}],"count":1}
     * tag : null
     * id : 0
     * adIndex : -1
     */
    data class DataBean(@SerializedName("dataType")     val dataType: String,
                        @SerializedName("itemList")     val itemList: List<ItemBean>,
                        @SerializedName("count")        val count: Int) {

        /**
         * type : banner
         * data : {"dataType":"Banner","id":1691,"title":"开眼请你看电影","description":"","image":"http://img.kaiyanapp.com/1eaf8827688ea3b910b7b6b6cb192a5f.png?imageMogr2/quality/60/format/jpg","actionUrl":"eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E4%B8%80%E5%8F%B7%E6%94%BE%E6%98%A0%E5%8E%85&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fnew_article.html%3Fnid%3D1516%26shareable%3Dtrue%26type%3DarticleTopic%26cid%3D1691","adTrack":[],"shade":false,"label":{"text":"","card":"","detail":null},"labelList":[],"header":{"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null},"autoPlay":false}
         * tag : null
         * id : 0
         * adIndex : -1
         */
        data class ItemBean(@SerializedName("type")     val dataType: String,
                            @SerializedName("data")     val data: ItemDataBean,
                            @SerializedName("tag")      val tag: Any,
                            @SerializedName("id")       val id: Int,
                            @SerializedName("adIndex")  val adIndex: Int) {

            /**
             * dataType : Banner
             * id : 1691
             * title : 开眼请你看电影
             * description :
             * image : http://img.kaiyanapp.com/1eaf8827688ea3b910b7b6b6cb192a5f.png?imageMogr2/quality/60/format/jpg
             * actionUrl : eyepetizer://webview/?title=%E5%BC%80%E7%9C%BC%E4%B8%80%E5%8F%B7%E6%94%BE%E6%98%A0%E5%8E%85&url=https%3A%2F%2Fwww.kaiyanapp.com%2Fnew_article.html%3Fnid%3D1516%26shareable%3Dtrue%26type%3DarticleTopic%26cid%3D1691
             * adTrack : []
             * shade : false
             * label : {"text":"","card":"","detail":null}
             * labelList : []
             * header : {"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null}
             * autoPlay : false
             */
            data class ItemDataBean(@SerializedName("dataType")     val dataType: String,
                                    @SerializedName("id")           val id: Int,
                                    @SerializedName("description")  val description: String,
                                    @SerializedName("image")        val image: String,
                                    @SerializedName("actionUrl")    val actionUrl: String,
                                    @SerializedName("adTrack")      val adTrack: List<*>,
                                    @SerializedName("shade")        val shade: Boolean,
                                    @SerializedName("label")        val label: Label,
                                    @SerializedName("labelList")    val labelList: List<*>,
                                    @SerializedName("header")       val header: Header,
                                    @SerializedName("autoPlay")     val autoPlay: Boolean) {
                /**
                 * label : {"text":"","card":"","detail":null}
                 */
                data class Label(@SerializedName("text")            val text: String,
                                 @SerializedName("card")            val card: String,
                                 @SerializedName("detail")          val detail: Any)

                /**
                 * header : {"id":0,"title":null,"font":null,"subTitle":null,"subTitleFont":null,"textAlign":"left","cover":null,"label":null,"actionUrl":null,"labelList":null,"rightText":null,"icon":null,"description":null}
                 */
                data class Header(@SerializedName("id")             val id: Int,
                                  @SerializedName("title")          val title: String,
                                  @SerializedName("font")           val font: Any,
                                  @SerializedName("subTitle")       val subTitle: String,
                                  @SerializedName("subTitleFont")   val subTitleFont: Any,
                                  @SerializedName("textAlign")      val textAlign: String,
                                  @SerializedName("cover")          val cover: Any,
                                  @SerializedName("label")          val label: String,
                                  @SerializedName("actionUrl")      val actionUrl: String,
                                  @SerializedName("labelList")      val labelList: Any,
                                  @SerializedName("rightText")      val rightText: String,
                                  @SerializedName("icon")           val icon: String,
                                  @SerializedName("description")    val description: String)
            }
        }
    }
}