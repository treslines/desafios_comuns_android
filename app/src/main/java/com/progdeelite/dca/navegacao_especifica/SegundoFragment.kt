package com.progdeelite.dca.navegacao_especifica

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.progdeelite.dca.MainActivity
import com.progdeelite.dca.R
import com.progdeelite.dca.SegundaActivity
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentMainToSecondGraphBinding
import com.progdeelite.dca.databinding.FragmentSegundoBinding
import com.progdeelite.dca.util.navigateToNavGraph

class SegundoFragment : Fragment(R.layout.fragment_segundo) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentSegundoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // VEJA VIDEO COMO PERSONALIZAR FONTES: XXXXXXXXX
        binding.openSpecificScreenInNewNavGraph.setOnClickListener {
            navigateToNavGraph(MainActivity::class.java, R.id.hideKeyboardFragment)
        }
    }

}