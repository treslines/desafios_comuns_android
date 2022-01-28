package com.progdeelite.dca.orientation

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentOrientationBinding


// VERSÃO: 1 - DECLARATIVA GERAL (PREFERENCIAL)
// VERSÃO: 2 - IMPERATIVA EM CÓDIGO (EVITE SEMPRE QUE PUDER)
// VERSÃO: 3 - DECLARATIVA XML MODO NINJA, VC NUNCA VIU!

class OrientationFragment : Fragment(R.layout.fragment_orientation) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentOrientationBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ATENCÃO: SETANDO UMA ORIENTAçÃO AQUI, TENS QUE TE ASSEGURAR QUE A
        // ORIENTACAO NAS OUTRAS VIEWS ESTEJAM NO FORMATO DESEJADO NOVAMENTE.
        // requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
        // requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
        // requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    }
}
