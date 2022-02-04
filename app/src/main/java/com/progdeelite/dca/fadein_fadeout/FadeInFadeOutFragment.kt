package com.progdeelite.dca.fadein_fadeout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentFadeInFadeOutBinding
import com.progdeelite.dca.util_extension.fadeIn
import com.progdeelite.dca.util_extension.fadeOut

// 1) COMO FAZER FADE-IN E FADE-OUT (EM CÓDIGO KOTLIN)
// 2) COMO DEFINIR EXTENSÕES PARA ESSAS ANIMACOES EM CÓDIGO
// 3) COMO USAR ESSES EFEITOS NA PRÁTICA

class FadeInFadeOutFragment : Fragment(R.layout.fragment_fade_in_fade_out) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentFadeInFadeOutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FAVORITAAAA QUE TU VAI PRECISAR!!! VÁ POR MIM! :)
        binding.fadeIn.setOnClickListener { binding.imageToFadeInAndOut.fadeIn(1000L) }
        binding.fadeOut.setOnClickListener { binding.imageToFadeInAndOut.fadeOut(2000L) }
    }
}