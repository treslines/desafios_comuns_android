package com.progdeelite.dca.recyclerview

import com.progdeelite.dca.custom_view.CustomMainMenuItem
import com.progdeelite.dca.databinding.CustomMenuItemBinding

// IMPLEMENTAçÃO REFERÊNCIA JA USANDO O VIEW BINDING

class CustomMainMenuItemViewHolder(
    private val binding: CustomMenuItemBinding, // VIDEO DO VIEWBINDING TA TOPEZEIRA! OLHA NA SEQUÊNCIA!
    private val clickListener: BaseAdapter.OnItemClickListener<CustomMainMenuItem>
) : BaseAdapter.BaseViewHolder<CustomMainMenuItem>(binding.root) {

    override fun bindView(item: CustomMainMenuItem) {
        binding.mainMenuItemTitle.text = item.mainMenuItemLabelText
        binding.mainMenuItemButton.setOnClickListener {
            clickListener.onItemClicked(item) // REPASSA O CLICK PARA QUEM CHAMOU
        }
    }
}