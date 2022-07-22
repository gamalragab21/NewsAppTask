package com.example.newsapptask.common.utils

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponseBody(
    @field:SerializedName("message")
    @Expose
    var message: String? = null,
    @Expose
    @field:SerializedName("errors") var errors: MutableMap<String, List<String>?>?,
    @Expose
    @field:SerializedName("status") var status: Boolean=false
)