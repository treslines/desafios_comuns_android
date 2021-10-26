package com.progdeelite.dca

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.databinding.FragmentMainBinding
import com.progdeelite.dca.util.navTo
import com.progdeelite.dca.util.showYoutubeVideo
import com.progdeelite.dca.util.toast
import com.progdeelite.dca.util.vibrate

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
            // | VIDEO: COMO EXIBIR FULL SCREEN DIALOGS: XXXXXXX                                   |
            // +-----------------------------------------------------------------------------------+
            fullscreenDialogs.setOnClickListener { navTo(R.id.fullscreenDialogFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO REALIZAR UM POLLING A UMA API: XXXXXXX                                |
            // +-----------------------------------------------------------------------------------+
            pollingApi.setOnClickListener { navTo(R.id.pollingApiFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO EXIBIR CUSTOM DIALOGS: XXXXXXX                                        |
            // +-----------------------------------------------------------------------------------+
            customDialogs.setOnClickListener { navTo(R.id.customDialogFragment) }
        }
    }
}