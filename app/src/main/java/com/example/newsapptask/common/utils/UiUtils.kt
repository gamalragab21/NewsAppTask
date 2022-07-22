package com.example.newsapptask.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.InputStream
import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun Activity.startActivity(clazz: Class<*>, finish: Boolean = false) {
    val intent = Intent(this, clazz)
    this.startActivity(intent)
    if (finish) this.finish()
}
fun NavController.navigateSafely(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null,
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(resId, args, navOptions, navExtras)
    }
}
fun String.toRequestBody(): RequestBody {
    return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), this)
}



fun Uri.multipartBody(filedName: String, context: Context): MultipartBody.Part {
    var path: String = getFilePath(context)// "file:///mnt/sdcard/FileName.mp3"
    var originalFile = File(path);
    var fileRequestBody: RequestBody =
        originalFile.absoluteFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())

    var file = MultipartBody.Part.createFormData(filedName, originalFile.name, fileRequestBody)

    return file
}

fun Uri.getFilePath(context: Context): String {
    var filePath = ""
    val wholeID = DocumentsContract.getDocumentId(this)
    val id: String = wholeID.split(":")[1]
    val column = arrayOf(MediaStore.Images.Media.DATA)
    val sel = MediaStore.Images.Media._ID + "=?"
    val cursor: Cursor? = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        column, sel, arrayOf<String>(id), null
    )

    val columnIndex = cursor!!.getColumnIndex(column[0])

    if (cursor!!.moveToFirst()) {
        filePath = cursor!!.getString(columnIndex)
    }
    cursor!!.close()
    return filePath
}


//region deserialize Any Object To String
public fun <T> deserializeObjectToString(`object`: T): String {
    return Gson().toJson(`object`)
}
fun roundNumber(doubleNumber: Double): String {
    return BigDecimal(doubleNumber).setScale(2, RoundingMode.HALF_EVEN).toString()
}

fun <T>List<T>.toArrayList(): ArrayList<T> {
    val arl = ArrayList<T>()
    for (`object` in this) {
        arl.add(`object`)
    }
    return arl
}



fun NavController.deleteCurrentFragmentAfterNavigate() = NavOptions.Builder()
    .setPopUpTo(this.currentDestination!!.id, true)
    .build()

fun String.getBitmapFromUrl(): Bitmap? {
    return try {
        val url = URL(this)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()
        val input: InputStream = connection.inputStream
        BitmapFactory.decodeStream(input)
    } catch (e: Exception) {
        Log.e("awesome", "Error in getting notification image: " + e.localizedMessage)
        null
    }
}

fun Fragment.snackbar(message: String) {
    requireView().hideKeyboard()
    Snackbar.make(
        requireView(),
        message,
        Snackbar.LENGTH_LONG
    ).show()

}

infix fun View.snackbar(message: String) {
    hideKeyboard()
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).show()

}

fun View.hideKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}




