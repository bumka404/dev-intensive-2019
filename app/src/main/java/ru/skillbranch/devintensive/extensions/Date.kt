package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    return when (val range = this.time - date.time){
        in -SECOND .. 0  -> "только что"
        in -45 * SECOND .. -SECOND -> "несколько секунд назад"
        in -75 * SECOND .. -45* SECOND -> "минуту назад"
        in -45 * MINUTE .. -75* SECOND  -> "${TimeUnits.MINUTE.plural((range/ MINUTE).toInt().absoluteValue)} назад"
        in -75 * MINUTE .. -45* MINUTE   -> "час назад"
        in -22 * HOUR .. -75* MINUTE -> "${TimeUnits.HOUR.plural((range/ HOUR).toInt().absoluteValue)} назад"
        in -26 * HOUR .. -22* HOUR -> "день назад"
        in -360 * DAY .. -26* HOUR -> "${TimeUnits.DAY.plural((range/ DAY).toInt().absoluteValue)} назад"
        in -Long.MAX_VALUE .. -360* DAY-> "более года назад"
        in 0 .. SECOND -> "только что"
        in SECOND .. 45 * SECOND -> "через несколько секунд"
        in 45* SECOND .. 75 * SECOND -> "через минуту"
        in 75* SECOND .. 45 * MINUTE -> "через ${TimeUnits.MINUTE.plural((range/ MINUTE).toInt())}"
        in 45* MINUTE .. 75 * MINUTE -> "через час"
        in 75* MINUTE .. 22 * HOUR  -> "через ${TimeUnits.HOUR.plural((range/ HOUR).toInt())}"
        in 22* HOUR .. 26 * HOUR -> "через день"
        in 26* HOUR .. 360 * DAY -> "через ${TimeUnits.DAY.plural((range/ DAY).toInt())}"
        in 360* DAY .. Long.MAX_VALUE -> "более чем через год"
        else -> ""
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String{
        return when (this){
            SECOND -> when(value.toString().last()) {
                '0','5','6','7','8','9' -> "$value секунд"
                '1' -> "$value секунду"
                '2','3','4' ->  "$value секунды"
                else -> "$value "
            }
            MINUTE -> {
                when(value.toString().last()){
                    '0','5','6','7','8','9' -> "$value минут"
                    '1' -> "$value минуту"
                    '2','3','4' -> "$value минуты"
                    else -> "$value "
                }
            }
            HOUR ->
                when(value.toString().last()){
                    '0','5','6','7','8','9' -> "$value часов"
                    '1' -> "$value час"
                    '2','3','4' -> "$value часа"
                    else -> "$value "
                }
            DAY ->
                when(value.toString().last()){
                    '0','5','6','7','8','9' -> "$value дней"
                    '1' -> "$value день"
                    '2','3','4' -> "$value дня"
                    else -> "$value"
                }
        }
    }
}
