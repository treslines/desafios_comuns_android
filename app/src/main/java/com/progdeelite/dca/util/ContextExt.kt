package com.progdeelite.dca.util

import android.content.Context
import androidx.annotation.StyleRes

const val GLOBAL_SHARED_PREFS = "br.com.progdeelite.global.sharedprefs"
const val GLOBAL_APP_STYLE = "GLOBAL_APP_STYLE"

fun Context.putStyleIntSharedPref(key:String, @StyleRes value:Int){
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    sharedPref?.edit()?.putInt(key, value)?.apply()
}

fun Context.putStringSharedPref(key:String, value:String){
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    sharedPref?.edit()?.putString(key, value)?.apply()
}

fun Context.putBooleanSharedPref(key:String, value:Boolean){
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    sharedPref?.edit()?.putBoolean(key, value)?.apply()
}

fun Context.getIntSharedPref(key:String): Int{
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    return sharedPref?.getInt(key, -1) ?: -1
}

fun Context.getStringSharedPref(key:String): String{
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    return sharedPref?.getString(key, "") ?: ""
}

fun Context.getBooleanSharedPref(key:String): Boolean{
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    return sharedPref?.getBoolean(key, false) ?: false
}

fun Context.removeSharedPref(key:String){
    val sharedPref = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)
    sharedPref?.edit()?.remove(key)?.apply()
}