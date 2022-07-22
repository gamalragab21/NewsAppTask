package com.example.newsapptask.common

object Constants {

    const val API_KEY: String="LCtVr9qIRXtkYaKQt37rp7xxRfNSvFte"

    /************ Complex Preference ***********/
    const val PREF_FILE = "VODO_APP_PREF"
    const val MODE_PRIVATE = 0

    /************ Base Url ***********/
    const val BASE_URL="https://api.nytimes.com/svc/news/"

    /************ User Flag ***********/
    const val IS_LOGIN = "IS_LOGIN"
    const val USER_DATA = "USER_DATA"
    const val USER_LANG = "USER_LANG"


}


typealias NewsAppR=com.example.newsapptask.R
typealias NewsDrawables=com.example.newsapptask.R.drawable
typealias NewsIds=com.example.newsapptask.R.id
const val TAG="MYAPPLICATION_TASK"