package com.progdeelite.dca.start_for_result

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {
    private lateinit var binding: FragmentResultBinding

    companion object {
        const val KEY_SELECTED_IMAGE_URI = "key_selected_image_uri"
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)

        // PEGAR ENDERECO DA IMAGEM E EXIBI-LA NA TELA
        val imageUriExtra = arguments?.getString(KEY_SELECTED_IMAGE_URI)
        Glide.with(this).load(imageUriExtra).into(binding.imageView)

        binding.navBack.setOnClickListener { findNavController().navigateUp() }
    }
}