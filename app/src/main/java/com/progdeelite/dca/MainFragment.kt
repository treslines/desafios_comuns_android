package com.progdeelite.dca

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.databinding.FragmentMainBinding
import com.progdeelite.dca.util_extension.navTo
import com.progdeelite.dca.util_extension.showYoutubeVideo
import com.progdeelite.dca.util_extension.toast
import com.progdeelite.dca.util_extension.vibrate


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        with(binding) {
            // +-----------------------------------------------------------------------------------+
            // | VIDEO: SOLICITAR PERMISSÕES COM API NOVA DA GOOGLE: https://youtu.be/grYUKZDTzVA  |
            // +-----------------------------------------------------------------------------------+
            requestPermissions.setOnClickListener { navTo(R.id.setResultFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: LANCAR ACTIVITY, FRAGMENTS E OBTER RESULTADO: https://youtu.be/mhm096S_qrA |
            // +-----------------------------------------------------------------------------------+
            startForResults.setOnClickListener { navTo(R.id.requestResultFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: ESCONDER TECLADO: https://youtu.be/OzK1fJi9FiQ                             |
            // +-----------------------------------------------------------------------------------+
            hideKeyboard.setOnClickListener { navTo(R.id.hideKeyboardFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: VIBRAR CELULAR: https://youtu.be/ogxgiaCq_24                               |
            // +-----------------------------------------------------------------------------------+
            vibrateCellphone.setOnClickListener { toast("vibrei! :)"); vibrate(1000) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO BLOQUEAR / IMPEDIR CAPTURAS DE TALAS: https://youtu.be/7zUdUYiu8Rs    |
            // +-----------------------------------------------------------------------------------+
            captureScreen.setOnClickListener { showYoutubeVideo("7zUdUYiu8Rs") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO VERIFICAR CONEXÃO COM INTERNET: https://youtu.be/DpyxLwibE0M          |
            // +-----------------------------------------------------------------------------------+
            appNoInternet.setOnClickListener { navTo(R.id.checkAppInternetFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR / GRAVAR VIDEOS DO ANDROID STUDIO: https://youtu.be/1vB46ujfVrA |
            // +-----------------------------------------------------------------------------------+
            createVideo.setOnClickListener { showYoutubeVideo("1vB46ujfVrA") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO FORCAR DARK MODE NA WEB VIEW: https://youtu.be/aMuHOlTNL9E            |
            // +-----------------------------------------------------------------------------------+
            darkTheme.setOnClickListener { navTo(R.id.darkWebViewFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR ÍCONES ADAPTÁVEIS E LEGACY: https://youtu.be/FNQ3DQSVd30        |
            // +-----------------------------------------------------------------------------------+
            adaptiveIcons.setOnClickListener { showYoutubeVideo("FNQ3DQSVd30") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO VISUALIZAR ERROS COM TIMBER EM PRODUCÃO: https://youtu.be/rz8O8V4Ho1M |
            // +-----------------------------------------------------------------------------------+
            logErrors.setOnClickListener { showYoutubeVideo("rz8O8V4Ho1M") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO AUTOMATIZAR VIEW BINDING SEUS FRAGMENTS: https://youtu.be/qivrch6qxQw |
            // +-----------------------------------------------------------------------------------+
            delegateViewBinding.setOnClickListener { showYoutubeVideo("qivrch6qxQw") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO DIFERENCIAR EMULADOR DE APARELHO FISICO: https://youtu.be/A14WEDpWjds |
            // +-----------------------------------------------------------------------------------+
            checkEmulator.setOnClickListener { navTo(R.id.checkEmulatorFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: ANIMACÕES DE CAMPOS E TEXTOS: https://youtu.be/4WMmin8vnU0                 |
            // +-----------------------------------------------------------------------------------+
            animFields.setOnClickListener { navTo(R.id.animFieldsFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO PERSISTIR EM MEMÓRIA (CHAVE-VALOR): https://youtu.be/XBqY-3MPjkg      |
            // +-----------------------------------------------------------------------------------+
            saveInMemory.setOnClickListener { showYoutubeVideo("XBqY-3MPjkg") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIPTOGRAFAR DADOS SENSIVEIS: https://youtu.be/aJqZ38-bZUc            |
            // +-----------------------------------------------------------------------------------+
            saveInMemoryCriptography.setOnClickListener { showYoutubeVideo("aJqZ38-bZUc") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO REAGIR AO BOTÃO DE BACK EM FRAGMENTS: https://youtu.be/8pvQ-dTaRGI    |
            // +-----------------------------------------------------------------------------------+
            pressBackButton.setOnClickListener { navTo(R.id.backPressedFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO USAR A BIOMETRIA DO SEU CELULAR: https://youtu.be/xpN94rgPkds         |
            // +-----------------------------------------------------------------------------------+
            showBiometry.setOnClickListener { navTo(R.id.biometryFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR FULL SCREEN DIALOGS: https://youtu.be/DRkS0NWNAvg              |
            // +-----------------------------------------------------------------------------------+
            fullscreenDialogs.setOnClickListener { navTo(R.id.fullscreenDialogFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO REALIZAR UM POLLING A UMA API: https://youtu.be/OX2KhMbm0_I           |
            // +-----------------------------------------------------------------------------------+
            pollingApi.setOnClickListener { navTo(R.id.pollingApiFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR CUSTOM DIALOGS: https://youtu.be/W8La6tMRv14                   |
            // +-----------------------------------------------------------------------------------+
            // customDialogs.setOnClickListener { navTo(R.id.customDialogFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: CUSTOM NAVIGATION ANIMATION: https://youtu.be/d9O7sjlN6JQ                  |
            // +-----------------------------------------------------------------------------------+
            // 1) DEFINIR ACTION COM ANIMACÃO DE TRANSICÃO
            // 2) CRIAR ARQUIVOS DE DURACÃO (integer.xml)
            // 3) CRIAR ARQUIVOS DE ANIMAçÃO OU USAR AS ANIMAçÕES PADRÃO
            // 4) APLICAR ANIMAçÃO NA PRÁTICA
            customDialogs.setOnClickListener { navTo(R.id.action_mainFragment_to_customDialogFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR SPLASH SCREEN MODERNO SEM XML: https://youtu.be/cMXE8PN-qIc    |
            // +-----------------------------------------------------------------------------------+
            splashScreen.setOnClickListener { showYoutubeVideo("cMXE8PN-qIc") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR UMA BARRA DE NAVEGAçÃO: https://youtu.be/1mG3-I8bof0           |
            // +-----------------------------------------------------------------------------------+
            bottomNavigationView.setOnClickListener { showYoutubeVideo("1mG3-I8bof0") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR UMA BOTTOM SHEET DIALOG: https://youtu.be/vku9pMNHT9o          |
            // +-----------------------------------------------------------------------------------+
            bottomSheetDialog.setOnClickListener { navTo(R.id.bottomSheetDialogFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR CONTENT PROVIDER: https://youtu.be/dcBqUVUwBx4                  |
            // +-----------------------------------------------------------------------------------+
            contentProvider.setOnClickListener { showYoutubeVideo("dcBqUVUwBx4") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO ANIMAR TRANSIçÃO DE TELAS NO NAV GRAPH: https://youtu.be/d9O7sjlN6JQ  |
            // +-----------------------------------------------------------------------------------+
            animatedScreenTranstions.setOnClickListener { showYoutubeVideo("d9O7sjlN6JQ") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO MAPEAR RESPOSTAS E EXCEçÕES DE APIs: https://youtu.be/vYO3oSyDgQA     |
            // +-----------------------------------------------------------------------------------+
            mapResponses.setOnClickListener { showYoutubeVideo("vYO3oSyDgQA") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR AMBIENTES DE PROD,STAGE,DEV: https://youtu.be/DLezbqDBzA0       |
            // +-----------------------------------------------------------------------------------+
            defineEnvironment.setOnClickListener { showYoutubeVideo("DLezbqDBzA0") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO LIMPAR COOKIES DO SEU APLICATOVO: https://youtu.be/rjAmpDV0IvM        |
            // +-----------------------------------------------------------------------------------+
            clearCookies.setOnClickListener { showYoutubeVideo("rjAmpDV0IvM") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR CLIENT HTTP COM KTOR: https://youtu.be/yTqByD4v7Ik              |
            // +-----------------------------------------------------------------------------------+
            clientHttp.setOnClickListener { showYoutubeVideo("yTqByD4v7Ik") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR APP DEMO PARA PLAYSTORE: https://youtu.be/-rld8-UH5d8           |
            // +-----------------------------------------------------------------------------------+
            demoAppPlaystore.setOnClickListener { showYoutubeVideo("-rld8-UH5d8") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO DECLARAR/DEFINIR FUNçÃO DE EXTENSÃO: https://youtu.be/8rcbHgu1uNQ     |
            // +-----------------------------------------------------------------------------------+
            kotlinExtensions.setOnClickListener { showYoutubeVideo("8rcbHgu1uNQ") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO:  COMO MIGRAR DADOS APP LEGADO PARA APP NOVO: https://youtu.be/HANcH98pU6I  |
            // +-----------------------------------------------------------------------------------+
            migration.setOnClickListener { showYoutubeVideo("HANcH98pU6I") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: VIEWHOLDER E VIEWADAPTER PARA RECYCLERVIEW: https://youtu.be/2PBMBNFdG_E   |
            // +-----------------------------------------------------------------------------------+
            recyclerviewBaseAdapter.setOnClickListener { showYoutubeVideo("2PBMBNFdG_E") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: ESTRUTURA BASE MVP ANDROID: https://youtu.be/4PiOchwZV7g                   |
            // +-----------------------------------------------------------------------------------+
            mvp.setOnClickListener { navTo(R.id.myViewFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: ITEM DECORATOR RECYCLERVIEW: https://youtu.be/r6TmXq1Rc7U                  |
            // +-----------------------------------------------------------------------------------+
            dividerRecyclerview.setOnClickListener { navTo(R.id.itemDecoratorFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: FLOATING LAYOUT/VIEWS : https://youtu.be/BhWGDWzNw1w                       |
            // +-----------------------------------------------------------------------------------+
            floatingViews.setOnClickListener { navTo(R.id.loginFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Text highligher (uma mão na roda) : https://youtu.be/XTP0CpsyMZU           |
            // +-----------------------------------------------------------------------------------+
            textHighlighter.setOnClickListener { navTo(R.id.textHighlighterFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Search view / componente de busca / filtros : https://youtu.be/dXun9d5T340 |
            // +-----------------------------------------------------------------------------------+
            searchView.setOnClickListener { navTo(R.id.searchViewFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Restaurar Ações Botões (Listeners): https://youtu.be/7uscLRBxr1w           |
            // +-----------------------------------------------------------------------------------+
            restoreListeners.setOnClickListener { navTo(R.id.restoreableActionFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Alterar cores statusbar(Barra de Status): https://youtu.be/nY_wnMbRjcw     |
            // +-----------------------------------------------------------------------------------+
            changeStatusbarColors.setOnClickListener { navTo(R.id.statusBarDayFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Como customizar fontes: https://youtu.be/ZCNq349mN78                       |
            // +-----------------------------------------------------------------------------------+
            customFont.setOnClickListener { navTo(R.id.customFontFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Texto Furta-Cor Camaleão: https://youtu.be/rDslK9ulizw                     |
            // +-----------------------------------------------------------------------------------+
            textFurtaCor.setOnClickListener { navTo(R.id.textFurtaCorFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Navegar para tela específica: https://youtu.be/CwwU1D91elg                 |
            // +-----------------------------------------------------------------------------------+
            navSpecificScreen.setOnClickListener { navTo(R.id.mainToSecondGraphFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: como criar imagens 9-patch: https://youtu.be/kAeU1XmT2Vk                   |
            // +-----------------------------------------------------------------------------------+
            ninePatchFiles.setOnClickListener { navTo(R.id.ninePatchFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Estilos personalizados: https://youtu.be/6HPBpeBXlXs                       |
            // +-----------------------------------------------------------------------------------+
            customStyles.setOnClickListener { navTo(R.id.customStyleFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Animação zoom infinito: https://youtu.be/Ewc1fn2uwhE                       |
            // +-----------------------------------------------------------------------------------+
            zoomInfinito.setOnClickListener { navTo(R.id.zoomInfiniteFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Animação profissionais com lottie: https://youtu.be/Ylijfgwu7f4            |
            // +-----------------------------------------------------------------------------------+
            animComLottie.setOnClickListener { navTo(R.id.lottieFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Controle de orientação: https://youtu.be/6CUQs6U55ww                       |
            // +-----------------------------------------------------------------------------------+
            orientation.setOnClickListener { navTo(R.id.orientationFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Botões Material Design Personalizados: https://youtu.be/szx4z57YQbE        |
            // +-----------------------------------------------------------------------------------+
            customButtons.setOnClickListener { navTo(R.id.customButtonFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Listas de produtos com Recyclerview: https://youtu.be/t856vskK9Ao          |
            // +-----------------------------------------------------------------------------------+
            productList.setOnClickListener { navTo(R.id.recyclerViewListFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Componentes Personalizados: https://youtu.be/szx4z57YQbE                   |
            // +-----------------------------------------------------------------------------------+
            customComponents.setOnClickListener { navTo(R.id.customComponentsFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Componentes Personalizados: https://youtu.be/xxxxxxxxxxxx                  |
            // +-----------------------------------------------------------------------------------+
            hideShowStatusBar.setOnClickListener { navTo(R.id.hideShowStatusBarFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Efeitos FadeIn / FadeOut Kotlin: https://youtu.be/xxxxxxxxxxxx             |
            // +-----------------------------------------------------------------------------------+
            fadeInFadeOut.setOnClickListener { navTo(R.id.fadeInFadeOutFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Mapeando objetos em Kotlin: https://youtu.be/xxxxxxxxxxxx                  |
            // +-----------------------------------------------------------------------------------+
            mapObjects.setOnClickListener { navTo(R.id.mapObjectsFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Zoom In no clique: https://youtu.be/xxxxxxxxxxx                            |
            // +-----------------------------------------------------------------------------------+
            // zoomNoClique.setOnClickListener { navTo(R.id.zoomOnClickFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: Listas com linha do tempo: https://youtu.be/xxxxxxxxxxx                    |
            // +-----------------------------------------------------------------------------------+
            // timeline.setOnClickListener { navTo(R.id.linhaDoTempoFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: processo de onboarding com progress steps: https://youtu.be/xxxxxxxxxxx    |
            // +-----------------------------------------------------------------------------------+
            // onboarding.setOnClickListener { navTo(R.id.onboardingFragment) }
        }
    }
}