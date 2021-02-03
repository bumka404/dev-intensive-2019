package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

fun String.truncate(value : Int = 16) : String {
    val cut = this.substring(value)
    var result = this.subSequence(0, value)
    while(result.last() == ' '){
        result = result.subSequence(0, result.lastIndex)
    }
    return "$result${if (cut.isBlank()) "" else "..."}"
}

//remove & < > ' "
fun String.stripHtml() : String{
    val pattern1 : Pattern = Pattern.compile("<.+?>")
    val pattern2 : Pattern = Pattern.compile("&.+?;")
    val pattern3 : Pattern = Pattern.compile("'.+?'")
    val pattern4 : Pattern = Pattern.compile("\".+?\"")
    if (this == null || this.isEmpty()) {
        return this
    }
    val v1 = pattern1.matcher(this).replaceAll("")
    val v2 = pattern2.matcher(v1).replaceAll("")
    val v3 = pattern3.matcher(v2).replaceAll("")
    return pattern4.matcher(v3).replaceAll("").replace("\\s+".toRegex(), " ")
}