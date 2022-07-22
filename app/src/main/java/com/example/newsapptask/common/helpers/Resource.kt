package com.example.newsapptask.common.helpers


sealed class Resource<T>(val data : T? = null , val message : String? = null,val errors: MutableMap<String, List<String>?>?=null) {
    class Success<T>(data : T) : Resource<T>(data)
//    class Error<T>(message : String?, data : T? = null) : Resource<T>(data , message)
    class Error<T>(message : String?, errors: MutableMap<String, List<String>?>?, data : T? = null) : Resource<T>(data , message,errors)
    class Loading<T>(data : T? = null) : Resource<T>(data)
}