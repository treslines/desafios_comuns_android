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

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: SOLICITAR PERMISSÕES COM API NOVA DA GOOGLE: https://youtu.be/grYUKZDTzVA  |
        // +-----------------------------------------------------------------------------------+
        binding.requestPermissions.setOnClickListener { navTo(R.id.setResultFragment) }

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: LANCAR ACTIVITY, FRAGMENTS E OBTER RESULTADO: https://youtu.be/mhm096S_qrA |
        // +-----------------------------------------------------------------------------------+
        binding.startForResults.setOnClickListener { navTo(R.id.requestResultFragment) }

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: ESCONDER TECLADO: https://youtu.be/OzK1fJi9FiQ                             |
        // +-----------------------------------------------------------------------------------+
        binding.hideKeyboard.setOnClickListener { navTo(R.id.hideKeyboardFragment) }

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: VIBRAR CELULAR: https://youtu.be/ogxgiaCq_24                               |
        // +-----------------------------------------------------------------------------------+
        binding.vibrateCellphone.setOnClickListener { toast("vibrei! :)"); vibrate(1000) }

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: COMO BLOQUEAR / IMPEDIR CAPTURAS DE TALAS: https://youtu.be/7zUdUYiu8Rs    |
        // +-----------------------------------------------------------------------------------+
        binding.captureScreen.setOnClickListener { toast("Apenas o vídeo explica!") }

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: COMO VERIFICAR CONEXÃO COM INTERNET: https://youtu.be/DpyxLwibE0M          |
        // +-----------------------------------------------------------------------------------+
        binding.appNoInternet.setOnClickListener { navTo(R.id.checkAppInternetFragment) }
    }
}