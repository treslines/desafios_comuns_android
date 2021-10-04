package com.progdeelite.dca

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.databinding.FragmentMainBinding
import com.progdeelite.dca.util.navTo

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        
        // solicitar permissões com a nova API da google
        binding.requestPermissions.setOnClickListener { navTo(R.id.setResultFragment) }

        // lançar activity e fragments e obter resultados
        binding.startForResults.setOnClickListener { navTo(R.id.requestResultFragment) }
    }
}