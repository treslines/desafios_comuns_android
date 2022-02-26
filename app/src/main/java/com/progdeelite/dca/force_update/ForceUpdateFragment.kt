package com.progdeelite.dca.force_update

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentForceUpdateBinding
import com.progdeelite.dca.util_extension.showDialog

// 1) COMO CRIAR UM DIÁLOGO DE FORCE UPDATE
// 2) COMO E QUANDO USA-LO (POR QUE IMPORTANTE?)
// 3) COMO SABER QUE DEVO ATUALUZAR SEM TER UM BACKEND

class ForceUpdateFragment : Fragment(R.layout.fragment_force_update) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentForceUpdateBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.successActionButton.setOnClickListener {
            // *******************************************
            // IMPORTANTE ********************************
            // *******************************************
            // ESSE METODO VC VAI CHAMAR DENTRO DO ONCREATE DA SUA
            // MAIN-ACTIVITY OU PRIMEIRO PONTO DE ENTRADA
            showAppOutdatedDialog()
        }
    }

    private fun showAppOutdatedDialog(): AlertDialog {
        // algum mecanismo que compara com ${BuildConfig.VERSION_NAME}
        return showDialog(
            title = "Update necessário!" /*getString(R.string.sua_string_de_tradução)*/,
            message = "Esse Update atualizará segurança e performance!" /*getString(R.string.sua_string_de_tradução)*/,
            cancelable = false,
            positiveButtonClickListener = {
                Intent(Intent.ACTION_VIEW)
                    .apply { Uri.parse("market://details?id=${com.progdeelite.dca.BuildConfig.APPLICATION_ID}") }
                    .let { startActivity(it) }
            },
            positiveButtonLabel = "Atualizar",
            negativeButtonLabel = "Fechar App",
            negativeButtonClickListener = {
                requireActivity().finish()
            }
        )
    }
}