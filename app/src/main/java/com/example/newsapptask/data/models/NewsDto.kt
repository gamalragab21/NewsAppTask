package com.example.newsapptask.data.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NewsDto(
    @SerializedName("copyright")
    @Expose
    var copyright: String,
    @SerializedName("num_results")
    @Expose
    var numResults: Int,
    @SerializedName("results")
    @Expose
    var resultDtos: List<ResultDto>,
    @SerializedName("status")
    @Expose
    var status: String
)