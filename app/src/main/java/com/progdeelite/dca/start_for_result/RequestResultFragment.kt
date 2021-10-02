package com.progdeelite.dca.start_for_result

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentRequestResultBinding
import com.progdeelite.dca.start_for_result.ResultFragment.Companion.KEY_SELECTED_IMAGE_URI
import com.progdeelite.dca.util.navTo
import com.progdeelite.dca.util.shouldRequestPermission
import com.progdeelite.dca.util.toast

class RequestResultFragment : Fragment(R.layout.fragment_request_result) {
    private lateinit var binding: FragmentRequestResultBinding

    // DEFINIR O LAUNCHER PARA A ACTIVITY QUE NOS VAI RETORNAR O RESULTADO
    private lateinit var fileChooserLauncher: ActivityResultLauncher<String>

    // ASSUNTO DA AULA ANTERIOR: SOLICITANDO PERMISSÕES - OLHA NA SEQUÊNCIA
    private lateinit var requestPermissionsForFileChooser: ActivityResultLauncher<Array<String>>
    private val fileChooserPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fileChooserLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                uri?.let {
                    navTo(
                        R.id.resultFragment,
                        bundleOf(Pair(KEY_SELECTED_IMAGE_URI, uri.toString()))
                    )
                }
                // CASO ELE NÃO NAVEGUE, ELE RETORNA PARA TELA QUE LANCOU A SOLICITACÃO
            }

        // ASSUNTO DA AULA ANTERIOR: SOLICITANDO PERMISSÕES - OLHA NA SEQUÊNCIA
        requestPermissionsForFileChooser = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionResult ->
            val permissionsIdentified = permissionResult.all { it.key in fileChooserPermissions }
            val permissionsGrant = permissionResult.all { it.value == true }
            if (permissionsIdentified && permissionsGrant) {
                // VAMOS LANCAR UMA ACTIVITY PARA OBTER O RESULTADO
                fileChooserLauncher.launch("image/*")
            } else {
                val deniedPermissions = permissionResult.map { perm ->
                    if (perm.value == false) perm.key else ""
                }.filter { it.isNotEmpty() }.toList()
                showWhyPermissionsAreNeeded(deniedPermissions)
            }
        }
    }

    private fun showWhyPermissionsAreNeeded(deniedPermissions: List<String>) {
        // DA SUBSCRIBE PARA NÃO PERDER A AULA DE CAIXAS DE DIÁLOGOS CUSTOMIZADAS! :)
        val msg = StringBuilder()
        msg.append("READ_EXTERNAL_STORAGE \n")
        msg.append("WRITE_EXTERNAL_STORAGE \n")
        toast("Você precisa nos conceder as permissões:\n$msg")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRequestResultBinding.bind(view)

        binding.requestResultButton.setOnClickListener {
            if (shouldRequestPermission(fileChooserPermissions)) {
                requestPermissionsForFileChooser.launch(fileChooserPermissions)
            } else {
                // SE JA TENHO A PERMISSÃO, LANCAMOS A ACTIVITY DIRETAMENTE
                fileChooserLauncher.launch("image/*")
            }
        }
    }
}