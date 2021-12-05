package com.progdeelite.dca

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.progdeelite.dca.databinding.ActivityMainBinding

// VANTAGEM DE TER UM SINGLE PAGE APPLICATION, VOCÊ FAZ A
// CONFIGURACÃO UMA ÚNICA VEZ EM UM LUGAR CENTRAL!
class MainActivity : AppCompatActivity() { // IMPORTANTE: SE USAR SPLASH, LEIA ABAIXO

    // +-----------------------------------------------------------------------------------+
    // | ********** IMPORTANTE SE USAR SPLAH COM A LIB NOVA DO ANDROID 12 *************    |
    // +-----------------------------------------------------------------------------------+
    // NORMALMENTE EU INTANCIO MINHAS CLASSES E FRAGMENTS ASSIM, COMO NA LINHA ABAIXO:
    // class MainActivity : AppCompatActivity(R.layout.activity_main)
    // POREM A LIB DE SPLASH SCREEN LANCA UM ERRO BIZARRO ALEGANDO QUE TENHO QUE ESTENDER
    // DE "Theme.AppCompat" QUANTO NA VERDADE A UNICA COISA QUE TENHO QUE FAZER É NÃO
    // INSTANCIAR O LAYOU NO CONSTRUTOR, MAS SIM APÓS A INSTALACÃO COMO FEITO NA LINHA 48


    // +-----------------------------------------------------------------------------------+
    // | VIDEO: COMO CRIAR UMA BOTTOM NAVIGATION VIEW: https://youtu.be/1mG3-I8bof0        |
    // +-----------------------------------------------------------------------------------+
    // 1) adicionar no layout
    // 2) criar menu de navegação
    // 3) definir binding e obter navController
    // 4) fazer o setup do navigation view usando o navigation graph

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!.findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PREVINE SCREENSHOTS, ESCONDE CONTEÚDO SENSÍVEL
        // ANDO O APP ESTA EM SEGUNDO PLANO. MUITO ÚTIL
        // QUANDO VOCÊ TRABALHA COM ALGUM TIPO DE APP
        // QUE EXIGE MAIOR SEGURANCA OU TRATA DADOS
        // SENSÍVEIS. BLOQUEIA CAPTURAS DE TELA!

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: BLOQUEAR SCREEN SHOTS E THUMBNAILS: https://youtu.be/7zUdUYiu8Rs           |
        // +-----------------------------------------------------------------------------------+
        //preventScreenshotsAndRecentAppThumbnails()


        // +-----------------------------------------------------------------------------------+
        // | VIDEO: SPLASH SCREEN ANDROID 12: https://youtu.be/cMXE8PN-qIc                     |
        // +-----------------------------------------------------------------------------------+
        // 1) Definir dependency no build.gradle
        // 2) Criar logo / drawable - ou especificar drawable-v31
        // 3) Definir stilo dia e noite
        // 4) especificar no manifest
        // 5) Usar delay na main activity
        // 6) Tomar cuidado com um detalhes importante
        installSplashScreen()
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: COMO CRIAR UMA ACTIONBAR/TOOLBAR BAR: https://youtu.be/RbOCBzHIwSw         |
        // +-----------------------------------------------------------------------------------+
        setupToolbar()

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: COMO CRIAR UMA BOTTOM NAVIGATION VIEW: https://youtu.be/1mG3-I8bof0        |
        // +-----------------------------------------------------------------------------------+
        setupBottomNavigation()
    }

    // +-----------------------------------------------------------------------------------+
    // | VIDEO: COMO CRIAR UMA BOTTOM NAVIGATION VIEW: https://youtu.be/1mG3-I8bof0        |
    // +-----------------------------------------------------------------------------------+
    private fun setupBottomNavigation() {
        with(binding.bottomNavigation) { setupWithNavController(navController) }
    }


    // +----------------------------------------------------------------------------------------+
    // | VIDEO: COMO CRIAR UMA TOP ACTION BAR COM NAV CONTROLLER: https://youtu.be/RbOCBzHIwSw  |
    // +----------------------------------------------------------------------------------------+
    // 1) definir toolbar no xml da main activity
    // 2) configurar o theme da sua aplicação para nao estourar esse erro aqui
    // 3) configurar a action bar para carregar os titulos direto do navigation graph
    // 4) fazer o setup dela dentro da main activity

    private fun setupToolbar() {
        // Caused by: java.lang.IllegalStateException: This Activity already has an action bar
        // supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and
        // set windowActionBar to false in your theme to use a Toolbar instead.
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.apply { setupActionBarWithNavController(navController) }
        // Da a habilidade de navegar de volta na seta do toolbar
        binding.mainToolbar.setNavigationOnClickListener { navController.navigateUp() }
    }

    fun setToolbarNavigationIcon(icon: Drawable?) {
        binding.mainToolbar.navigationIcon = icon
    }

    fun setToolbarNavigationTitle(title: String?) {
        binding.mainToolbar.title = title
    }

}