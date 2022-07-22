package com.example.newsapptask.common.utils


import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * helper class to parse errors thrown from retrofit
 */
object ErrorUtils {
        fun parseError(t: Throwable?): ErrorResponseBody {
            var errorResponse = ErrorResponseBody("UnKnown Error Occur",null,false)
            when (t) {
                is HttpException -> {
                    t.response()?.errorBody()?.string()?.let {
                        errorResponse = Gson().fromJson(it, ErrorResponseBody::class.java)
                    }
//                    when (t.code()) {
//                        401 -> {
//                            errorResponse = ErrorResponseBody(401, error = t.message())
//                        }
//                    }
                }
                is IOException, is UnknownHostException, is SocketTimeoutException -> {
                    errorResponse = ErrorResponseBody("Check Internet Connection",null,false)
                }
            }
            return errorResponse
        }
    }
