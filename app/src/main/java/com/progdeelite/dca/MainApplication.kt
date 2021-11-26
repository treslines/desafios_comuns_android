package com.progdeelite.dca

import android.app.Application
import com.progdeelite.dca.logcat_timber.CustomLogger
import com.progdeelite.migration.MigrationUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

// +-----------------------------------------------------------------+
// | Aula de distribuição de apps e logs atraves de Appcenter        |
// +-----------------------------------------------------------------+
// Log Crashes over AppCenter
// import com.microsoft.appcenter.AppCenter
// import com.microsoft.appcenter.crashes.Crashes

// +-----------------------------------------------------------------+
// | Aula de injeção de dependência com Koin                         |
// +-----------------------------------------------------------------+
// Init Dependency Injection Koin
// import org.koin.android.ext.koin.androidContext
// import org.koin.android.ext.koin.androidLogger
// import org.koin.core.logger.Level

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
        setupDefaultExceptionHandler() // https://youtu.be/zu9MOl95LKs
//        setupDependencyInjection()

        // +-----------------------------------------------------------------+
        // | VIDEO: COMO MIGRAR SETTINGS PARA APP NOVO: XXXXXXXXX            |
        // +-----------------------------------------------------------------+
        MigrationUtil(this).migrateOnlyOnce()
    }

// +-----------------------------------------------------------------+
// | Aula de injeção de dependência com Koin                         |
// +-----------------------------------------------------------------+
//    private fun setupDependencyInjection() = initKoin {
//        androidLogger(Level.NONE)
//        androidContext(this@MainApplication)
//        modules(
//            appModule,
//        )
//    }

    // +-----------------------------------------------------------------+
    // | Log e o timber customizado: https://youtu.be/rz8O8V4Ho1M        |
    // +-----------------------------------------------------------------+
    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // +---------------------------------------------------------------------+
            // | Logs usados em PROD para crashlytics e outros fins da sua empresa   |
            // +---------------------------------------------------------------------+
            Timber.plant(CustomLogger())

            // +-----------------------------------------------------------------+
            // | Distribuição de apps e logs atraves de Appcenter                |
            // +-----------------------------------------------------------------+
            // SE VOCE OPTAR POR LOGAR CRASHES COM APPCENTER, CONFIGURACÕES NECESSÁRIAS
            // Crashes.setEnabled(true)
            // AppCenter.setLogLevel(Log.ERROR)
            // AppCenter.start(this, BuildConfig.APP_CENTER_ID, Crashes::class.java)
        }
    }

    // +-------------------------------------------------------------------+
    // | Instalando DefaultExceptionHandler: https://youtu.be/zu9MOl95LKs  |
    // +-------------------------------------------------------------------+
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