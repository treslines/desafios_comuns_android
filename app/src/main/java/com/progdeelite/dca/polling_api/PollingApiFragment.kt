package com.progdeelite.dca.polling_api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentPollingApiBinding
import com.progdeelite.dca.util.hasInternet
import com.progdeelite.dca.util.polling
import com.progdeelite.dca.util.toast

// 1) Definir escopos de coroutine
// 2) Definir extensão de polling
// 3) Usar na método recursivo na prática
class PollingApiFragment : Fragment(R.layout.fragment_polling_api) {
    private val binding by viewBinding(FragmentPollingApiBinding::bind)

    private var stateSuccess = false
    private var stateError = false
    private var stateHasInternet = false
    private var stateCanceled = false

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.simulateSuccessBtn.setOnClickListener {
            stateSuccess = !stateSuccess
        }

        binding.simulateErroBtn.setOnClickListener {
            stateError = !stateError
        }

        binding.simulateNoInternetBtn.setOnClickListener {
            stateHasInternet = !stateHasInternet
        }

        binding.simulateCanceledBtn.setOnClickListener {
            stateCanceled = !stateCanceled
        }

        binding.startPollingBtn.setOnClickListener {
            polling(
                isOffline = { !hasInternet() },
                onOffline = { toast("Sem Internet") },
                isCompleted = { stateSuccess },
                onCompleted = { toast("sucesso") },
                isError = { stateError },
                onError = { toast("erro") },
                isCanceled = { stateCanceled },
                onCanceled = { toast("Polling cancelado") }
            )
        }
    }
}