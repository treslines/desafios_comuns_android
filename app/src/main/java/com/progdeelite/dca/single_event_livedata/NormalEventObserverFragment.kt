package com.progdeelite.dca.single_event_livedata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentNormalEventObserverBinding
import com.progdeelite.dca.util_extension.navTo
import com.progdeelite.dca.util_extension.toast

// 1) COMO CRIAR UM LIVEDAta QUE SO EMITE EVENTOS DE MUDANçAS
// 2) QUANDO ISSO PODE VIRAR UM PROBLEMA (É MAIS COMUM DO QUE VC PENSA)
// 3) COMO USAR ISSO NA PRÁTICA
class NormalEventObserverFragment : Fragment(R.layout.fragment_normal_event_observer) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentNormalEventObserverBinding::bind)

    private val myViewModel: MyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.observableNormalEvent.observe(viewLifecycleOwner){
            toast("dispara eventos na mudança e no registro(criação)")
        }
        myViewModel.observableSingleEvent.observe(viewLifecycleOwner){
            toast("dispara eventos APENAS nas mudanças")
        }

        binding.triggerNormalEventBtn.setOnClickListener {
            myViewModel.changeNormalEvent("Evento Normal")
        }

        binding.triggerSingleEventBtn.setOnClickListener {
            myViewModel.changeSingleEvent("Evento Unico")
        }

        binding.navNextViewBtn.setOnClickListener {
            navTo(R.id.singleEventObserverFragment)
        }

    }
}
