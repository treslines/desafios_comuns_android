package com.progdeelite.dca.migration

import android.content.Context
import com.progdeelite.dca.util_extension.*
import com.progdeelite.dca.migration.PreferenceKey.APP_BUILD_NUMBER
import com.progdeelite.dca.migration.PreferenceKey.APP_VERSION
import com.progdeelite.dca.migration.PreferenceKey.MIGRATION_COMPLETED
import com.progdeelite.dca.migration.PreferenceKey.USER_LOGIN_ACTIVE
import com.progdeelite.dca.migration.PreferenceKey.USER_LOGIN_AUTOMATIC
import com.progdeelite.dca.migration.PreferenceKey.USER_LOGIN_SAVE_ID

// 1) Definir Novas Preference Keys
// 2) Definir Classe de Migração
// 3) Usar a classe de Migração em application

class MigrationUtil(val context: Context) {

    fun migrateOnlyOnce(){
        if(!context.getBooleanSharedPref(MIGRATION_COMPLETED)){
            migrate()
        }
        context.putBooleanSharedPref(MIGRATION_COMPLETED, true)
    }

    private fun migrate() {
        // local definition since we are gonna migrate only once
        // and don't want to have useless classes in our code base
        val version = "version"
        val build = "build"
        val autoLogBool = "AUTO_LOGON"
        val saveIdBool = "save-id"
        val active = "active"

        // migrate old entries to new app
        // VEJA VIDEO COMO CRIAR EXTENSÕES - LIKE & SUBSCRIBE :)
        context.putStringSharedPref(APP_VERSION, context.getStringSharedPref(version))
        context.putStringSharedPref(APP_BUILD_NUMBER, context.getStringSharedPref(build))
        context.putBooleanSharedPref(USER_LOGIN_AUTOMATIC, context.getBooleanSharedPref(autoLogBool))
        context.putBooleanSharedPref(USER_LOGIN_SAVE_ID, context.getBooleanSharedPref(saveIdBool))
        context.putBooleanSharedPref(USER_LOGIN_ACTIVE, context.getBooleanSharedPref(active))

        // clean up old entries
        context.removeSharedPref(version)
        context.removeSharedPref(build)
        context.removeSharedPref(autoLogBool)
        context.removeSharedPref(saveIdBool)
        context.removeSharedPref(active)
    }
}