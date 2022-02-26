package com.progdeelite.dca.single_event_livedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentSingleEventObserverBinding
import com.progdeelite.dca.util_extension.toast

class SingleEventObserverFragment : Fragment(R.layout.fragment_single_event_observer) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentSingleEventObserverBinding::bind)

    private val myViewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.observableNormalEvent.observe(viewLifecycleOwner){
            toast("dispara eventos na mudança e no registro(criação)")
        }
        myViewModel.observableSingleEvent.observe(viewLifecycleOwner){
            toast("dispara eventos APENAS nas mudanças")
        }
    }
}