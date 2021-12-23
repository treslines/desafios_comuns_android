package com.progdeelite.dca.recyclerview

import com.progdeelite.dca.databinding.CustomMenuItemBinding

// IMPLEMENTAçÃO REFERÊNCIA JA USANDO O VIEW BINDING

class CustomMainMenuItemViewHolder(
    private val binding: CustomMenuItemBinding, // VIDEO DO VIEWBINDING TA TOPEZEIRA! OLHA NA SEQUÊNCIA!
    private val clickListener: BaseAdapter.OnItemClickListener<CustomMenuItemBinding>
) : BaseAdapter.BaseViewHolder<CustomMenuItemBinding>(binding.root) {

    override fun bindView(item: CustomMenuItemBinding) {
        binding.mainMenuItemTitle.text = item.mainMenuItemTitle.text
        binding.mainMenuItemButton.text = item.mainMenuItemButton.text
        binding.mainMenuItemButton.setOnClickListener {
            clickListener.onItemClicked(item) // REPASSA O CLICK PARA QUEM CHAMOU
        }
    }
}