package com.swuniv.agefree.presentation.detection.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.DecimalFormat

fun String.printLog(tag: String? = null) {
    Log.d("+++ $tag", this)
}

fun Throwable.printError(tag: String? = null, message: String? = null) {
    Log.e("+++ $tag", "$message", this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Int.toWon(): String {
    val format = DecimalFormat("#,###")
    return "${format.format(this)}원"
}