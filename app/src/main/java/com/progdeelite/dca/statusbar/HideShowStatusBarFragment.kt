package com.progdeelite.dca.statusbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentHideShowStatusBarBinding
import com.progdeelite.dca.databinding.FragmentZoomInfiniteBinding
import com.progdeelite.dca.util_extension.hideSystemBars
import com.progdeelite.dca.util_extension.showSystemBars

// 1) COMO ESCONDER / EXBIR A STATUS BAR (BARRA DE STATUS) EXPLICAR CASO DE USO
// 2) COMO LEVAR EM CONSIDERAçÃO VERSÕES LEGADAS (OLHAR FRAGMENT)
// 3) COMO DEFINIR E USAR AS EXTENSÕES NA PRÁTICA

class HideShowStatusBarFragment : Fragment(R.layout.fragment_hide_show_status_bar) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentHideShowStatusBarBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FAVORITAAAA QUE CEDO OU TARDE TU VAI PRECISAR!
    }

    override fun onResume() {
        super.onResume()
        requireActivity().hideSystemBars()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().showSystemBars()
    }
}