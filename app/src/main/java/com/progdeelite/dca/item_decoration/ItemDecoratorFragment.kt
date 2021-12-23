package com.progdeelite.dca.item_decoration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentItemDecoratorBinding
import com.progdeelite.dca.recyclerview.CustomMainMenuItemAdapter
import com.progdeelite.dca.util.attachHeader
import com.progdeelite.dca.util.setSimpleDefaultDivider
import com.progdeelite.dca.util.setCustomHeadlineDivider
import com.progdeelite.dca.util.toast

// 0) Fragment que vai exibir nossa lista (recyclerView)
// 1) Criar uma divisória simples com um drawable (divider)
// 2) Criar divisórias de texto entre itens da lista (text-divider)
// 3) Criar um Header para o topo da lista
// 4) Te dá pontos de partida com implementações referências (CustomItemDecorator)
// 5) Criar extensões bacanas pra facilitar tua vida (mão na roda!!!)

class ItemDecoratorFragment : Fragment(R.layout.fragment_item_decorator), View.OnClickListener {

    // VEJA TBM VIDEO COMO DELEGAR VIEW BINDING
    private val binding by viewBinding(FragmentItemDecoratorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            val items = mutableListOf(
                Pair("titulo1","button1"),
                Pair("titulo2","button2"),
                Pair("titulo3","button3"),
                Pair("titulo4","button4"),
                Pair("titulo5","button5"),
                Pair("titulo6","button6"),
                Pair("titulo7","button7"),
                Pair("titulo8","button8"),
                Pair("titulo9","button9")
            )
            val customAdapter = CustomMainMenuItemAdapter(requireContext(), items,this@ItemDecoratorFragment)
            itemDecoratorRecylerview.adapter = customAdapter
            // VOCÊ PODE COMBINAR DIVERSOS DECORADORES
            itemDecoratorRecylerview.attachHeader("Header Personalizado No Topo")
            itemDecoratorRecylerview.setCustomHeadlineDivider()
            itemDecoratorRecylerview.setSimpleDefaultDivider(R.drawable.item_decorator_divider)

            // Sticky Header
            // https://medium.com/android-news/the-beauty-of-a-sticky-itemdecoration-db18171f5e26
        }
    }

    override fun onClick(v: View?) {
        toast("Já te inscrevesse no canal?")
    }
}