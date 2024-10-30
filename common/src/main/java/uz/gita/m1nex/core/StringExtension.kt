package uz.gita.m1nex.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

fun String.isValidPassword(): Boolean {
    if (this.isEmpty()) {
        return false
    }

    if (length < 8) {
        return false
    }

    if (this.contains(" ")) {
        return false
    }
    return true
}
fun CharSequence.toFormat(int: Int): String {
    val sb = StringBuilder()
    val text = this.reversed()
    for (i in indices) {
        if (i % int == 0 && i != 0) {
            sb.append(" ")
        }
        sb.append(text[i])
    }
    return sb.toString().reversed()
}

fun main(){
    println("12321312312".toFormat(3))
}
fun formatTimestampDay(timestamp: Long): String {
    // Convert epoch timestamp to Date object
    val date: Date = Date(timestamp)

    // Define date format pattern with 24-hour clock ("HH")
    val formatter: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")

    // Set the time zone to user's system default (similar to original code)
    formatter.timeZone = TimeZone.getDefault()

    // Format the Date object and return the string
    return formatter.format(date)
}