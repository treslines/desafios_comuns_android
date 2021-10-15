package com.progdeelite.dca

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.databinding.FragmentMainBinding
import com.progdeelite.dca.util.navTo
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
            captureScreen.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO VERIFICAR CONEXÃO COM INTERNET: https://youtu.be/DpyxLwibE0M          |
            // +-----------------------------------------------------------------------------------+
            appNoInternet.setOnClickListener { navTo(R.id.checkAppInternetFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR / GRAVAR VIDEOS DO ANDROID STUDIO: https://youtu.be/1vB46ujfVrA |
            // +-----------------------------------------------------------------------------------+
            createVideo.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO FORCAR DARK MODE NA WEB VIEW: https://youtu.be/aMuHOlTNL9E            |
            // +-----------------------------------------------------------------------------------+
            darkTheme.setOnClickListener { navTo(R.id.darkWebViewFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO CRIAR ÍCONES ADAPTÁVEIS E LEGACY: https://youtu.be/FNQ3DQSVd30        |
            // +-----------------------------------------------------------------------------------+
            adaptiveIcons.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO VISUALIZAR ERROS COM TIMBER EM PRODUCÃO: https://youtu.be/rz8O8V4Ho1M |
            // +-----------------------------------------------------------------------------------+
            logErrors.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO DELEGAR O VIEW BINDING EM SEUS FRAGMENTS: XXXXXXX                     |
            // +-----------------------------------------------------------------------------------+
            delegateViewBinding.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO DIFERENCIAR EMULADOR DE APARELHO FISICO: XXXXXXX                      |
            // +-----------------------------------------------------------------------------------+
            // checkEmulator.setOnClickListener { navTo(R.id.checkEmulatorFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO PERSISTIR EM MEMÓRIA (CHAVE-VALOR): XXXXXXX                           |
            // +-----------------------------------------------------------------------------------+
            // saveInMemory.setOnClickListener { toast("Apenas o vídeo explica!") }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: ANIMACÕES DE CAMPOS E TEXTOS: XXXXXXX                                      |
            // +-----------------------------------------------------------------------------------+
            // animFields.setOnClickListener { navTo(R.id.animFieldsFragment) }

            // +-----------------------------------------------------------------------------------+
            // | VIDEO: COMO USAR A BIOMETRIA DO SEU CELULAR: XXXXXXX                              |
            // +-----------------------------------------------------------------------------------+
            // showBiometry.setOnClickListener { navTo(R.id.biometryFragment) }
        }
    }
}