package com.progdeelite.dca.anim_fields

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentAnimFieldsBinding
import com.progdeelite.dca.util.hideKeyboard
import com.progdeelite.dca.util.shake
import com.progdeelite.dca.util.toast
import com.progdeelite.dca.util.vibrate

// 1) DEFINIR ANIMACÃO COMO EXTENSÃO EM CIMA DE VIEWEXT
// 2) CRIAR UM LAYOUT E SIMULAR UMA VERIFICACÃO
// 3) ANIMAR VERIFICACÃO EM MOMENTO DE FALHA

class AnimFieldsFragment : Fragment(R.layout.fragment_anim_fields) {

    // VEJA TBM VIDEO COMO DELEGAR VIEW BINDING
    private val binding by viewBinding(FragmentAnimFieldsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cpfButton.setOnClickListener {
            // SIMULACAO DE VERIFICACÃO BOBA SIMPLES PARA FINS DIDÁTICOS
            if (binding.cpf.text.toString().length == 15) {
                hideKeyboard()        // VEJA VIDEO COMO ESCONDER TECLADO: https://youtu.be/OzK1fJi9FiQ
                toast("CPF válido!")
                binding.cpf.setText("")
            } else {
                hideKeyboard()
                vibrate(500L)  // VEJA VIDEO COMO VIBRAR APP: https://youtu.be/ogxgiaCq_24
                binding.cpf.setText("")
                binding.cpfLayout.shake { toast("CPF não existe!") }
            }
        }
    }
}