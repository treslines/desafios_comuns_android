package com.progdeelite.dca.bottom_sheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentBottomSheetDialogBinding

// 1) Criar classes utilitarias (view holder, view item, view adapter etc)
// 2) Definir layout e itens a serem exibidos
// 3) Criar a classe de tela flutuante (bottom sheet fragment)
// 4) Usar o bottom sheet dialog na pratica
// Video: https://youtu.be/vku9pMNHT9o
class BottomSheetDialogFragment : Fragment(R.layout.fragment_bottom_sheet_dialog) {

    // ANTES :(
    //private lateinit var binding: FragmentCheckAppInternetBinding

    // DEPOIS! :) COM DELEGACÃO E EXTENSÃO!
    private val binding by viewBinding(FragmentBottomSheetDialogBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ANTES :(
        //binding = FragmentCheckAppInternetBinding.bind(view)

        binding.pressBottomSheetBtn.setOnClickListener {
            TicketSelectionFragment(true).show(
                parentFragmentManager,
                BottomSheetDialogFragment::class.simpleName
            )
        }
    }
}