package com.progdeelite.dca.util

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.progdeelite.dca.R

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

// +-------------+
// | Navigation  |
// +-------------+
fun Context.navTo(@IdRes destination: Int, @IdRes popBackStackExclusive: Int? = null){
    val fragManager = (this as FragmentActivity).supportFragmentManager
    val navHostFragment = fragManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController

    popBackStackExclusive?.let { id -> navController.popBackStack(id, false) }
    navController.navigate(destination)
}

// +-----------+
// | Dialogs   |
// +-----------+
fun Context.showExitDialog(@StringRes title: Int, @StringRes message: Int, exitAction: () -> Unit = {}) {
    AlertDialog.Builder(this, R.style.DcaDialog)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(this.getString(android.R.string.ok)) { _, _ -> exitAction() }
        .setNegativeButton(getString(android.R.string.cancel)) { _, _ -> }
        .setCancelable(false)
        .show()
}

fun Context.getSharedPrefs() = getSharedPreferences(GLOBAL_SHARED_PREFS, Context.MODE_PRIVATE)