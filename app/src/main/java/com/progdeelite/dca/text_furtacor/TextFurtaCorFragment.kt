package com.progdeelite.dca.text_furtacor

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentTextFurtaCorBinding
import com.progdeelite.dca.util.startFurtaCorAnim

class TextFurtaCorFragment : Fragment(R.layout.fragment_text_furta_cor) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentTextFurtaCorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // VEJA VIDEO COMO PERSONALIZAR FONTES: XXXXXXXXX
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.gesture_points)
        binding.furtaCor.typeface = typeface

        val fromBlue = -0xfa9b01
        val toBlack = -0x1000000
        startFurtaCorAnim(binding.furtaCor, fromBlue, toBlack)
    }
}