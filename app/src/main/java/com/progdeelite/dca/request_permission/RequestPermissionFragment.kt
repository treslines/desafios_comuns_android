package com.progdeelite.dca.request_permission

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentRequestPermissionBinding
import com.progdeelite.dca.util.shouldRequestPermission
import com.progdeelite.dca.util.toast

// 1) DEFINIR PERMISSÕES NO MANIFEST
// 2) DEFINIR O LAUNCHER QUE VAI OFERECER UMA CAIXA DE DIALOGO AO USUÁRIO NO onCreate()
// 3) SOLICITAR PERMISSÕES INDICANDO QUAIS PERMISSÕES SÃO NECESSÁRIAS

class RequestPermissionFragment : Fragment(R.layout.fragment_request_permission) {
    private lateinit var binding: FragmentRequestPermissionBinding

    // NOVA API DE SOLICITACÃO DE PERMISSÕES QUE USA CALLBACKS
    private lateinit var fileChooserPermissionLauncher: ActivityResultLauncher<Array<String>>

    // PERMISSÕES QUE IREMOS SOLICITAR AO USUÁRIO EM TEMPO DE EXECUçÃO
    private val fileChooserPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fileChooserPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionResult ->
            val permissionsIdentified = permissionResult.all { it.key in fileChooserPermissions }
            val permissionsGrant = permissionResult.all { it.value == true }
            if (permissionsIdentified && permissionsGrant) {
                toast("Permissões concedidas! Parabens!")
                // DA SUBSCRIBE PARA APRENDER COMO LANCAR ACTIVITY FOR RESULT NA PROXIMA AULA! :)
            } else {
                val deniedPermissions = permissionResult.map { perm ->
                    if (perm.value == false) perm.key else ""
                }.filter { it.isNotEmpty() }.toList()
                // EXPLICA AO USUÁRIO, PORQUE ELE PRECISA CONCEDER AS PERMISSÕES
                showWhyPermissionsAreNeeded(deniedPermissions)
            }
        }
    }

    // EXPLICA PORQUE AS PERMISSÕES SÃO NECESSÁRIAS AO USUÁRIO
    private fun showWhyPermissionsAreNeeded(deniedPermissions: List<String>) {
        // DA SUBSCRIBE PARA NÃO PERDER A AULA DE CAIXAS DE DIÁLOGOS CUSTOMIZADAS! :)
        val msg = StringBuilder()
        msg.append("READ_EXTERNAL_STORAGE \n")
        msg.append("WRITE_EXTERNAL_STORAGE \n")
        toast("Você precisa nos conceder as permissões:\n$msg")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRequestPermissionBinding.bind(view)

        binding.requestPermissionButton.setOnClickListener {
            if (shouldRequestPermission(fileChooserPermissions)) {
                fileChooserPermissionLauncher.launch(fileChooserPermissions)
            } else {
                toast("Permissões já foram dadas! Parabens!")
                // DA SUBSCRIBE PARA NÃO PERDER A PRÓXIMA AULA DE REQUEST RESULT! :)
            }
        }
    }
}