package com.progdeelite.dca.navegacao_especifica

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.progdeelite.dca.R
import com.progdeelite.dca.SegundaActivity
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentMainToSecondGraphBinding
import com.progdeelite.dca.util_extension.navigateToNavGraph

// 1) COMO CRIAR MULTIPLOS GRÁFICOS DE NAVEGAçÃO
// 2) COMO ABRIR UM NAV GRAPH E NAVEGAR PAR TELA ESPECÍFICA
// 3) COMO USAR ISSO NA PRÁTICA

class MainToSecondGraphFragment : Fragment(R.layout.fragment_main_to_second_graph) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentMainToSecondGraphBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // VEJA VIDEO COMO PERSONALIZAR FONTES: XXXXXXXXX
        binding.openSecondNavGraph.setOnClickListener {
            navigateToNavGraph(SegundaActivity::class.java)
        }
    }

}