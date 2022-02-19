package com.progdeelite.dca.rating_dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentRatingDialogBinding
import com.progdeelite.dca.util_extension.feedback

// 1) COMO CRIAR UM RATING DIALOG CUSTOMIZADO
// 2) COMO MANTER O CONTROLE SOBRE A ORDEM DE EXIBIçÃO
// 3) COMO USA-LO NA PRÁTICA COM UMA EXTENSÃO

class RatingDialogFragment : Fragment(R.layout.fragment_rating_dialog) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentRatingDialogBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ratingDialogButton.setOnClickListener {
            binding.ratingDialogButton.feedback()
        }
    }
}