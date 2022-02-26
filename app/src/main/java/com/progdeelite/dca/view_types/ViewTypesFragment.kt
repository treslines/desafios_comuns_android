package com.progdeelite.dca.view_types

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentViewTypesBinding
import com.progdeelite.dca.util_extension.toast

// 1) COMO CRIAR RECYCLEVIEW COM VARIAS TIPOS DE VISUALIZACOES
// 2) COMO DEFINIR E IMPLEMENTAR OS VIEW TYPES E VIEW HOLDERS
// 3) COMO USAR ISSO NA PRÁTICA DE MANEIRA SUPER SIMPLES

class ViewTypesFragment : Fragment(R.layout.fragment_view_types), ViewItemAdapter.ClickListener {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentViewTypesBinding::bind)
    private lateinit var adapter: ViewItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ViewItemAdapter(this)
        val items = mutableListOf<ViewItem>()

        items.add(
            ViewItem.Title(
                R.string.view_item_title,
            )
        )
        items.add(
            ViewItem.Ad(
                R.drawable.route_66
            )
        )
        items.add(
            ViewItem.TitleSubTitle(
                R.string.view_item_title,
                R.string.view_item_subtitle,
            )
        )

        adapter.setItems(items.toList())
        binding.fragmentViewItem.adapter = adapter
    }

    override fun onListItemClicked(item: ViewItem) {
        toast("Item foi clicado!")
    }
}