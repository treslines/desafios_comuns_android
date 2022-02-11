package com.progdeelite.dca.plural

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentPluralBinding

// 1) COMO DIFERENCIAR PLURAIS (LAYOUT FRAGMENT)
// 2) COMO ESPECIFICAR PLURAIS NO XML
// 3) COMO USAR PLURAIS NA PRÁTICA

// https://developer.android.com/guide/topics/resources/string-resource
class PluralFragment : Fragment(R.layout.fragment_plural) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentPluralBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            buttonResultado.setOnClickListener {
                when {
                    entrada.text.toString().isBlank().not() -> {
                        try {
                            val numeroDigitado = entrada.text.toString().toInt()
                            val substituicao = numeroDigitado
                            val indicadorPlural = numeroDigitado
                            resultado.text =
                                resources.getQuantityString(R.plurals.quantidade, indicadorPlural, substituicao)
                        } catch (e: Exception) {
                            resultado.text = "Digite um número"
                        }
                    }
                    else -> resultado.text = "Digite um número"
                }
            }
        }
    }
}