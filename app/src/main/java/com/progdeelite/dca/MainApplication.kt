package com.progdeelite.dca

import android.app.Application
import com.progdeelite.dca.logcat_timber.CustomLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

// import com.microsoft.appcenter.crashes.Crashes

// 1) inserir dependência no build.gradle
// 2) criar a classe de log customizada para sua empresa usar me prod
// 3) criar sua classe de application para inicializar seus timbers
// 4) adicionar sua classe de application no manifest

// +-----------------------------------------------------------------+
// | Essa classe tem que ser definida no Manifest                    |
// +-----------------------------------------------------------------+
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupLogging()
        setupDefaultExceptionHandler()
    }

    // +-----------------------------------------------------------------+
    // | Inicializando o timber de log e o timber customizado            |
    // +-----------------------------------------------------------------+
    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // +---------------------------------------------------------------------+
            // | Logs usados em PROD para crashlytics e outros fins da sua empresa   |
            // +---------------------------------------------------------------------+
            Timber.plant(CustomLogger())

            // SE VOCE OPTAR POR LOGAR CRASHES COM APPCENTER, CONFIGURACÕES NECESSÁRIAS
            // Crashes.setEnabled(true)
            // AppCenter.setLogLevel(Log.ERROR)
            // AppCenter.start(this, BuildConfig.APP_CENTER_ID, Crashes::class.java)
        }
    }

    // +-----------------------------------------------------------------+
    // | Instalando DefaultExceptionHandler                             |
    // +-----------------------------------------------------------------+
    private fun setupDefaultExceptionHandler() {
        // pega o default Uncaught Exception handler para repassar os erros
        val existingHandler = Thread.getDefaultUncaughtExceptionHandler()
        // intercepta os erros, faz o que or preciso e so depois disso lança os erros
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            ClearableCoroutineScope(Dispatchers.Default).launch {
                Timber.v("clearing cookies")
                // Faça tudo que for pedido pela auditoria de segurança
            }
            existingHandler?.uncaughtException(thread, exception)
        }
    }
}