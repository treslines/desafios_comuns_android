package com.progdeelite.dca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.progdeelite.dca.util.preventScreenshotsAndRecentAppThumbnails

// VANTAGEM DE TER UM SINGLE PAGE APPLICATION, VOCÊ FAZ A
// CONFIGURACÃO UMA ÚNICA VEZ EM UM LUGAR CENTRAL!
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // PREVINE SCREENSHOTS, ESCONDE CONTEÚDO SENSÍVEL
        // ANDO O APP ESTA EM SEGUNDO PLANO. MUITO ÚTIL
        // QUANDO VOCÊ TRABALHA COM ALGUM TIPO DE APP
        // QUE EXIGE MAIOR SEGURANCA OU TRATA DADOS
        // SENSÍVEIS. BLOQUEIA CAPTURAS DE TELA!
        preventScreenshotsAndRecentAppThumbnails()
    }
}