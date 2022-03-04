package com.progdeelite.dca.secure_flag

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentSecureFlagBinding

// 1) COMO CONTORNAR O PROBLEMA DO SECURE FLAG CAUSADO PELA NOVA VERSÃO ANDROID 12
// 2) POR QUE ISSO SE TORNOU UM PROBLEMA OU RELEVANTE
// 3) UM PROPOSTA DE SOLUçÃO QUE USAMOS EM NOSSO APP

// REFERENCIAS DE RELATOS
// https://stackoverflow.com/questions/67223645/setting-flag-secure-does-not-work-when-added-to-onpause-in-android-11
// https://stackoverflow.com/questions/43274289/android-customizing-recent-apps-thumbnail-screenshot-by-default
class SecureFlagFragment : Fragment(R.layout.fragment_secure_flag) {

    // +-----------------------------------------------------------------------------------+
    // | VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw                       |
    // +-----------------------------------------------------------------------------------+
    private val binding by viewBinding(FragmentSecureFlagBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // +-----------------------------------------------------------------------------------+
        // | VIDEO: BLOQUEAR SCREEN SHOTS E THUMBNAILS: https://youtu.be/7zUdUYiu8Rs           |
        // +-----------------------------------------------------------------------------------+
        // A PARTIR DA VERSÃO ANDROID 12, RECENT THUMBNAILS NÃO FUNCIONA COMO ERA ANTES (TEM DIFERENçAS)
        //
        binding.hiddenTextMask.setOnTouchListener { _, event ->
            event?.let {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.hiddenTextMask.visibility = View.INVISIBLE
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        binding.hiddenTextMask.visibility = View.VISIBLE
                        true
                    }
                    else -> false
                }
            } ?: false
        }
    }
}