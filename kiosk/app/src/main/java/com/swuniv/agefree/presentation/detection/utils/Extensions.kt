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

fun String.convertOldColdHot(): String {
    return if (this == "cold") {
        "차갑게"
    } else {
        "뜨겁게"
    }
}

fun String.convertOldSoftDeep(): String {
    return if (this == "soft") {
        "연하게"
    } else if (this == "normal") {
        "기본"
    } else {
        "진하게"
    }
}

fun String.convertOldAmount(): String {
    return if (this == "normal") {
        "기본"
    } else if (this == "many") {
        "많이"
    } else {
        "아주 많이"
    }
}