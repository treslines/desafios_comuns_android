package com.progdeelite.dca.custom_style

import android.os.Bundle
import android.system.Os.bind
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentCustomStyleBinding

// 1) COMO CRIAR OS ARQUIVOS DE STYLES PERSONALIZADAS
// 2) COMO REFERENCIA-LOS E COMO LEVAR EM CONSIDERACAO VERSOES LEGADAS
// 3) COMO USAR AS NOVAS FONTES NA PRÁTICA

// LINK FONTE OFICIAL
// https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml#using-support-lib
class CustomStyleFragment : Fragment(R.layout.fragment_custom_style) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentCustomStyleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FAçA O QUE QUISER AQUI....
    }
}