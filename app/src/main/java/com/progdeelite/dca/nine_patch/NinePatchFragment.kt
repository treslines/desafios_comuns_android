package com.progdeelite.dca.nine_patch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentNinePatchBinding

// 1) COMO CRIAR BITMAPS DIMENSIONÁVEIS QUE NÃO PERDEM QUALIDADE
// 2) COMO TRANSFORMAR UM PNG EM UM 9-PATCH DENTRO DO ANDROID STUDIO
// 3) COMO EDITAR O NINE PATCH E USA-LO NA PRÁTICA

// LINK OFICIAL: https://developer.android.com/studio/write/draw9patch
class NinePatchFragment : Fragment(R.layout.fragment_nine_patch) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentNinePatchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FAçA O QUE QUISER AQUI....

    }
}