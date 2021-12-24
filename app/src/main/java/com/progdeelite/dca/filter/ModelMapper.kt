package com.progdeelite.dca.filter

import android.annotation.SuppressLint
import android.content.Context
import com.progdeelite.dca.R
import java.text.SimpleDateFormat
import java.util.*

object ModelMapper {

    fun getTimeComponentFromDateTime(dateTime: String): String {
        if (dateTime.isNotEmpty()) {
            return dateTime.split(" ")[1]
        }
        return ""
    }

    @SuppressLint("SimpleDateFormat")
    fun getGermanDateFromString(string: String): Date? {
        return SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(string)
    }

    @SuppressLint("SimpleDateFormat")
    fun getEnglishDateFromString(string: String): Date? {
        return SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(string)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeFromFilterKey(context: Context, filterItem: String): Date {
        val cal = Calendar.getInstance()

        when (filterItem) {
            context.getString(R.string.last_15_min) -> cal.add(Calendar.MINUTE, -15)
            context.getString(R.string.last_hour) -> cal.add(Calendar.HOUR, -1)
            context.getString(R.string.last_4_hours) -> cal.add(Calendar.HOUR, -4)
            context.getString(R.string.last_day) -> cal.add(Calendar.DAY_OF_YEAR, -1)

        }
        return cal.time
    }

    fun getEnglishTimeStringFromFilterKey(context: Context, filterItem: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        return formatter.format(getTimeFromFilterKey(context, filterItem))
    }

    fun getGermanTimeStringFromFilterKey(context: Context, filterItem: String): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.GERMAN)
        return formatter.format(getTimeFromFilterKey(context, filterItem))
    }
}