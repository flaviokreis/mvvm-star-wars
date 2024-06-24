package com.flaviokreis.datasource.utils

fun String.getIdFromUrl(): Int {
    val substring = if (this.last() == '/') this.substring(0, this.length -1) else this
    return substring
        .split("/")
        .last()
        .toIntOrNull() ?: -1
}