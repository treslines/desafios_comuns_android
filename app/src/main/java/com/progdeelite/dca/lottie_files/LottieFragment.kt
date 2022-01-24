package com.progdeelite.dca.lottie_files

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentLottieBinding

// 1) DEFININDO DEPENDÊNCIAS DA LIB DE ANIMAçÃO LOTTIE (gradle)
// 2) COMO E ONDE IMPORTAR ARQUIVOS DE ANIMACÃO (importar lottie: https://lottiefiles.com/)
// 3) CRIAR UM LAYOUT DE ANIMACÃO E USAR NA PRÁTICA (xml)

class LottieFragment : Fragment(R.layout.fragment_lottie) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentLottieBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FACA O QUE VOCE QUISER....
    }
}