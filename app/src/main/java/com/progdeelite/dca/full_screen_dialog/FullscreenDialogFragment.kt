package com.progdeelite.dca.full_screen_dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentFullscreenDialogBinding
import com.progdeelite.dca.util_extension.showFullscreenAlertDialog


// 1) Definir fullscreen dialog e layout
// 2) Definir style fullscreen
// 3) Criar extensão para re-utilizar onde quiser
// 4) Usar Dialog de Tela Cheia na prática

class FullscreenDialogFragment : Fragment(R.layout.fragment_fullscreen_dialog) {

    private val binding by viewBinding(FragmentFullscreenDialogBinding::bind)

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogBtn.setOnClickListener {
            showFullscreenAlertDialog(
                "JÁ É INSCRITO?",
                "RAPAZ, TAIS PERDENDO UM CONTEÚDO ARRETADO VISSE!!"
            )
        }
    }
}