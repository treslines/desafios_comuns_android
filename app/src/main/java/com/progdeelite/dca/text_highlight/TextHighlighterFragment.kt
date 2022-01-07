package com.progdeelite.dca.text_highlight

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.*
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentTextHighlighterBinding

// 1) Como formatar textos da maneira moderna com kotlin (Layout)
// 2) Como usar o SpannableStringBuilder
// 3) Usando na prática

class TextHighlighterFragment : Fragment(R.layout.fragment_text_highlighter) {

    // VEJA TBM VIDEO COMO DELEGAR VIEW BINDING
    private val binding by viewBinding(FragmentTextHighlighterBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            textHighlight.text = getFormattedText()
        }
    }

    private fun getFormattedText(): Spannable {
        val gold = resources.getColor(R.color.gold, null)
        val green = resources.getColor(R.color.teal_700, null)
        return SpannableStringBuilder()
            .color(green) { append("Gold text ") }
            .append("Normal text ")
            .scale(0.5F) { append("Text at half size ") }
            .backgroundColor(gold) { append("Background gold") }
            .bold { underline { italic { append(" Bold underlined and italic ") } } }
            .scale(1.5F) { append("Text with bigger size.") }
            .strikeThrough {append(" Cortado no meio") }
            .append(" Equação: x")
            .superscript{append("2")}
            .append(" = y e 10")
            .subscript{append("log(n)")}
            .append("\n\nsão formatações bonitas demais! :)")
    }
}