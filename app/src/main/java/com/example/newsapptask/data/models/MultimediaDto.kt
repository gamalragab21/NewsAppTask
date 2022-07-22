package com.example.newsapptask.data.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class MultimediaDto(
    @SerializedName("caption")
    @Expose
    var caption: String,
    @SerializedName("copyright")
    @Expose
    var copyright: String,
    @SerializedName("format")
    @Expose
    var format: String,
    @SerializedName("height")
    @Expose
    var height: Int,
    @SerializedName("subtype")
    @Expose
    var subtype: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("width")
    @Expose
    var width: Int
)