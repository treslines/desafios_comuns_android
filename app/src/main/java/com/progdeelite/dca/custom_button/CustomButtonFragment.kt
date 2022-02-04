package com.progdeelite.dca.custom_button

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentCustomButtonBinding
import com.progdeelite.dca.util_extension.toast

// 1) COMO CRIAR BOTÕES COM MATERIAL DESIGN
// 2) COM BORDA, SEM BORDA, ATIVO, INATIVO, EM FORMA DE TEXTO...
// 3) COMO CRIAR O EFEITO DE TOQUE EM IMAGE VIEWS (RIPPLE EFFECT)

class CustomButtonFragment : Fragment(R.layout.fragment_custom_button) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentCustomButtonBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // JÁ FAVORITA O VIDEO PORQUE TU VAI PRECISAR! 100%!!!
        binding.buttonFiltro.setOnClickListener { toast("Clicou!") }

    }
}