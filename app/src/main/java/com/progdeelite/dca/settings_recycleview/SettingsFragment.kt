package com.progdeelite.dca.settings_recycleview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentSettingsBinding
import com.progdeelite.dca.util_extension.toast

// 1) COMO CRIAR TELA DE CONFIGURAçÕES PERSONALIZADAS
// 2) COMO USAR TUDO QUE APRENDEMOS SOBRE VIEW TYPES
// 3) USANDO E EXIBINDO A TELA PERSONALIZADA

class SettingsFragment : Fragment(R.layout.fragment_settings), SettingsAdapter.ClickListener  {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private lateinit var adapter: SettingsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SettingsAdapter(this)
        val items = mutableListOf<SettingsViewItem>()

        items.add(
            SettingsViewItem.Route(
                R.drawable.route_66,
                R.string.card_route_title,
                R.string.card_route_subtitle
            )
        )
        items.add(
            SettingsViewItem.Map(
                R.drawable.map_line,
                R.string.card_bus_map_title,
                R.string.card_bus_map_subtitle
            )
        )
        items.add(
            SettingsViewItem.Parada(
                R.drawable.bus_stop,
                R.string.card_bus_stop_title,
                R.string.card_bus_stop_subtitle
            )
        )
        items.add(
            SettingsViewItem.Linha(
                R.drawable.bus_line,
                R.string.card_bus_line_title,
                R.string.card_bus_line_subtitle
            )
        )

        adapter.setItems(items.toList())
        binding.fragmentSettingsRecycler.adapter = adapter
    }

    override fun onListItemClicked(item: SettingsViewItem) {
        toast("fui clicado!")
    }
}