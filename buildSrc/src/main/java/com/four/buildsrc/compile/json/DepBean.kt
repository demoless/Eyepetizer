package com.four.buildsrc.compile.json
import com.google.gson.annotations.SerializedName

data class DepBean(@SerializedName("impl_list")
                   var implList: List<Data> = emptyList(),

                   @SerializedName("test_impl_list")
                   var testImplList: List<Data> = emptyList(),

                   @SerializedName("android_test_impl_list")
                   var androidTestImplList: List<Data> = emptyList(),

                   @SerializedName("api_list")
                   var apiList: List<Data> = emptyList()) {

    data class Data(var group: String = "",
                    var name: String = "",
                    var version: String = "",
                    var ext: String = "",

                    @SerializedName("project_path")
                    var projectPath: String? = null,
                    @SerializedName("file_path")
                    var filePath: String? = null
    )
}