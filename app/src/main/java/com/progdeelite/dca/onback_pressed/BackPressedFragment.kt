package com.progdeelite.dca.onback_pressed

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentBackPressedBinding
import com.progdeelite.dca.util_extension.toast
import timber.log.Timber

// 1) COMO ESCUTAR/REAGIR AO BOTÃO DE BACK DENTRO DE FRAGMENTS?
// 2) POR QUE FAZER ISSO NO FRAGMENT E NAO NA ACTIVITY?
// 3) COMO IMPLEMENTAR UMA INTERFACE ANONIMA EM KOTLIN
class BackPressedFragment : Fragment(R.layout.fragment_back_pressed) {

    private val binding by viewBinding(FragmentBackPressedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // FACA O QUE FOR PRECISO ANTES DO BACK SER EFETIVADO...
            clearData()
            logForCrashlytics()
            // ....
            findNavController().navigateUp()
        }

//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    logForCrashlytics()
//                }
//            })
    }

    private fun logForCrashlytics() {
        toast("Pressionei Back")
        Timber.d("LOGAR COMPORTAMENTO/ACÃO DO USUÁRIO SE NECESSÁRIO (CRASHLYTICS")
    }

    private fun clearData() {
        // EXEMPLO REMOVER TUDO DO CACHE E DOS CAMPOS
        binding.cpfEntry.text?.clear()
    }

}