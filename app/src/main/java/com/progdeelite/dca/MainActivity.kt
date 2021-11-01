package com.progdeelite.dca

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.progdeelite.dca.util.preventScreenshotsAndRecentAppThumbnails

// VANTAGEM DE TER UM SINGLE PAGE APPLICATION, VOCÊ FAZ A
// CONFIGURACÃO UMA ÚNICA VEZ EM UM LUGAR CENTRAL!
class MainActivity : AppCompatActivity() { // IMPORTANTE: SE USAR SPLASH, LEIA ABAIXO

    // +-----------------------------------------------------------------------------------+
    //    // | ********** IMPORTANTE SE USAR SPLAH COM A LIB NOVA DO ANDROID 12 *************    |
    //    // +-----------------------------------------------------------------------------------+
    //    // NORMALMENTE EU INTANCIO MINHAS CLASSES E FRAGMENTS ASSIM, COMO NA LINHA ABAIXO:
    //    // class MainActivity : AppCompatActivity(R.layout.activity_main)
    //    // POREM A LIB DE SPLASH SCREEN LANCA UM ERRO BIZARRO ALEGANDO QUE TENHO QUE ESTENDER
    //    // DE "Theme.AppCompat" QUANTO NA VERDADE A UNICA COISA QUE TENHO QUE FAZER É NÃO
    //    // INSTANCIAR O LAYOU NO CONSTRUTOR, MAS SIM APÓS A INSTALACÃO COMO FEITO NA LINHA 48


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PREVINE SCREENSHOTS, ESCONDE CONTEÚDO SENSÍVEL
        // ANDO O APP ESTA EM SEGUNDO PLANO. MUITO ÚTIL
        // QUANDO VOCÊ TRABALHA COM ALGUM TIPO DE APP
        // QUE EXIGE MAIOR SEGURANCA OU TRATA DADOS
        // SENSÍVEIS. BLOQUEIA CAPTURAS DE TELA!

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: BLOQUEAR SCREEN SHOTS E THUMBNAILS: XXXXXXXXX                              |
        // +-----------------------------------------------------------------------------------+
        preventScreenshotsAndRecentAppThumbnails()


        // +-----------------------------------------------------------------------------------+
        // | VIDEO: SPLASH SCREEN ANDROID 12: XXXXXXXXX                                        |
        // +-----------------------------------------------------------------------------------+
        // 1) Definir dependency no build.gradle
        // 2) Criar logo / drawable - ou especificar drawable-v31
        // 3) Definir stilo dia e noite
        // 4) especificar no manifest
        // 5) Usar delay na main activity
        // 6) Tomar cuidado com um detalhes importante
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                val delay = 500L // MAXIMO POSSIVEL PELA API 1s ou 1000L ANDROID 12
                override fun onPreDraw(): Boolean {
                    Thread.sleep(delay)
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            }
        )
    }
}