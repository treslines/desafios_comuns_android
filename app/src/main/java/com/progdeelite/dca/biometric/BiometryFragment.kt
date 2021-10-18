package com.progdeelite.dca.biometric

import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricPrompt.*
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentBiometryBinding
import com.progdeelite.dca.util.promptBiometricChecker
import com.progdeelite.dca.util.toast

// REFERÊNCIAS OFICIAIS
// https://developer.android.com/jetpack/androidx/releases/biometric
// https://developer.android.com/training/sign-in/biometric-auth

// 1) adicionar dependencia no build.gradle
// 2) definir uma extensão para obter o biometric prompt
// 3) usar ele passando os callbacks que deseja
class BiometryFragment : Fragment(R.layout.fragment_biometry) {

    // DEPOIS! :)
    private val binding by viewBinding(FragmentBiometryBinding::bind)

    // ANTES :(
    // private lateinit var binding: FragmentBiometryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ANTES :(
        // binding = FragmentBiometryBinding.bind(view)

        binding.biometryButton.setOnClickListener {
            promptBiometricChecker(
                "Desbloqueia Por Favor",
                "Use Seu FingerPrint",
                "Cancelar",
                confirmationRequired = true,
                null,
                { result ->
                    when (result.authenticationType) {
                        AUTHENTICATION_RESULT_TYPE_BIOMETRIC -> {
                            toast("sucesso fingerprint or face!")
                        }
                        AUTHENTICATION_RESULT_TYPE_UNKNOWN -> {
                            toast("sucesso por meio legado ou desconhecido")
                        }
                        AUTHENTICATION_RESULT_TYPE_DEVICE_CREDENTIAL -> {
                            toast("sucesso pin, pattern or password")
                        }
                    }
                },
                { error, errorMsg ->
                    toast("$error: $errorMsg")
                })
        }
    }
}