package com.progdeelite.dca.custom_font

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentCustomFontBinding
import com.progdeelite.dca.databinding.FragmentMainBinding

// 1) COMO OBTER ESSAS FONTES E ONDE DECLARA-LAS
// 2) COMO IMPORTAR E DECLARAR FONTES PERSONALIZADAS
// 3) COMO USAR AS NOVAS FONTES NA PRÁTICA (API NOVA E LEGADA)

// LINK FONTE OFICIAL
// https://developer.android.com/guide/topics/ui/look-and-feel/fonts-in-xml#using-support-lib
class CustomFontFragment : Fragment(R.layout.fragment_custom_font) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentCustomFontBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // VERSÕES ACIMA IGUAL 26
//         val typeface = resources.getFont(R.font.gesture_points)
//         binding.customFont.typeface = typeface

        // VERSÕES ABAIXO DE 26
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.gesture_points)
        binding.customFont.typeface = typeface
    }
}