package me.chnsh.cryptoindexing.extensions

import java.util.*

/**
 * author: Chintan Shah
 * project: crypto-indexing
 * date: 29/1/18
 */

fun Date.with(
    year: Int = -1,
    month: Int = -1,
    day: Int = -1,
    hour: Int = -1,
    minute: Int = -1,
    second: Int = -1,
    milliseconds: Int = -1
): Date =
    Calendar.getInstance().apply {
        time = this@with
        if (year > -1) set(Calendar.YEAR, year)
        if (month > -1) set(Calendar.MONTH, month)
        if (day > 0) set(Calendar.DATE, day)
        if (hour > -1) set(Calendar.HOUR_OF_DAY, hour)
        if (minute > -1) set(Calendar.MINUTE, minute)
        if (second > -1) set(Calendar.SECOND, second)
        if (milliseconds > -1) set(Calendar.MILLISECOND, milliseconds)
    }.time

fun Date.clearMinutes(): Date =
    Calendar.getInstance().apply {
        time = this@clearMinutes
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time

fun Date.clearTime(): Date =
    Calendar.getInstance().apply {
        time = this@clearTime
        set(Calendar.HOUR_OF_DAY, 0)
    }.time.clearMinutes()

fun Date.lastWeek(): Date =
    Calendar.getInstance().apply {
        time = this@lastWeek
        add(Calendar.WEEK_OF_MONTH, -1)
    }.time