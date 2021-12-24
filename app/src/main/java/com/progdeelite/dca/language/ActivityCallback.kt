package com.progdeelite.dca.language

// 1) Como se comunicar com mainActivity
// 2) Como definir callbacks? O que são callbacks?
// 3) Por quem implementar e como acessa-lo partindo dos fragments
// 4) Como usa-lo na prática?
interface ActivityCallback {

    fun showLoadingSpinner()

    fun hideLoadingSpinner()

    fun showAppBarBackButton(show: Boolean)

    fun showAppBarTitle(show: Boolean)

    fun showActionBar(show: Boolean)
}