package com.example.beloo.foodnixtest.language

import java.util.*

fun String.removeSpaces() = this.replace(" ", "")

fun String.trimInvisible() = this.trim { it <= ' ' }

fun String.toUpperEn() = this.toUpperCase(Locale.ENGLISH)

fun String.toLowerEn() = this.toLowerCase(Locale.ENGLISH)

/** if String is empty it will be converted to null */
fun String?.notEmpty(): String?= if (this.isNullOrEmpty()) null else this