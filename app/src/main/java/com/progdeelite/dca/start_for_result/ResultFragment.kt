package com.progdeelite.dca.start_for_result

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentResultBinding
import com.progdeelite.dca.start_for_result.RequestResultFragment.Companion.KEY_CHOOSER_BACK_BUTTON
import com.progdeelite.dca.start_for_result.RequestResultFragment.Companion.KEY_CHOOSER_REQUEST
import com.progdeelite.dca.util.navBack

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var binding: FragmentResultBinding

    // +-------------------------------------------------------------------+
    // | CLASSE QUE RECEBE ARGUMENTOS É A CLASSE QUE DISPONIBILIZA AS KEYS |
    // +-------------------------------------------------------------------+
    companion object {
        const val KEY_SELECTED_IMAGE_URI = "key_selected_image_uri"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)

        // +-------------------------------------------------------------------+
        // | PEGAR ENDERECO DA IMAGEM E EXIBI-LA NA TELA                       |
        // +-------------------------------------------------------------------+
        val imageUriExtra = arguments?.getString(KEY_SELECTED_IMAGE_URI)

        // +-------------------------------------------------------------------+
        // | GLIDE FOI ADICIONADO no build.gradle                              |
        // +-------------------------------------------------------------------+
        Glide.with(this).load(imageUriExtra).into(binding.imageView)

        binding.navBack.setOnClickListener {
            // +-------------------------------------------------------------------+
            // | DEVOLVER RESULTADO PARA TELA ANTERIOR                             |
            // +-------------------------------------------------------------------+
            setFragmentResult(
                KEY_CHOOSER_REQUEST,
                bundleOf(KEY_CHOOSER_BACK_BUTTON to "PRESSIONEI VOLTAR")
            )
            navBack()
        }
    }
}