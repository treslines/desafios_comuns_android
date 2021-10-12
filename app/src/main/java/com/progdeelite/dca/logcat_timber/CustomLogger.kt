package com.progdeelite.dca.logcat_timber

import android.util.Log
import timber.log.Timber

// import com.microsoft.appcenter.crashes.Crashes

// +-----------------------------------------------------------------+
// | Aqui você aprende a criar um timber customizado                 |
// +-----------------------------------------------------------------+
class CustomLogger : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (priority == Log.ERROR && throwable != null) {
            // +-------------------------------------------------------------------------+
            // | Logue o que quiser, use library de Crashes da microsoft AppCenter etc.  |
            // +-------------------------------------------------------------------------+
            Timber.e(throwable)
            // Crashes.trackError(throwable)
        }
    }
}