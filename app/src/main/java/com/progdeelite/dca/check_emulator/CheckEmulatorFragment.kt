package com.progdeelite.dca.check_emulator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentCheckEmulatorBinding
import com.progdeelite.dca.util_extension.EmulatorDetector
import com.progdeelite.dca.util_extension.toast

// 1) CRIAR PACOTE UTILS PARA RECURSOS COMUNS
// 2) DEFINIR SINGLETON EM PACOTE UTILS
// 3) USAR ELE NA PRÁTICA NO EXEMPLO A SEGUIR
// 4) PRA QUE SERVE ISSO? RECURSO NÃO DISPONIVEIS EM EMULADOR OU TRABALHO REMOTO

class CheckEmulatorFragment : Fragment(R.layout.fragment_check_emulator) {

    private val binding by viewBinding(FragmentCheckEmulatorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkEmulatorButton.setOnClickListener {
            when {
                EmulatorDetector.isEmulator() -> toast("É um Emulador!")
                else -> toast("É um aparelho físico!")
            }
        }
    }
}