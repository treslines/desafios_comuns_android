package com.progdeelite.dca.custom_components

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentCustomComponentsBinding
import java.math.BigDecimal

// 1) Criar componente, definir styles
// 2) criar atributos, criar
class CustomComponentsFragment : Fragment(R.layout.fragment_custom_components) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentCustomComponentsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // slider normal
        binding.sliderImperative.binding.title.text = "Slider Imperativo"
        binding.sliderImperative.valueList = mutableListOf(BigDecimal(100), BigDecimal(200), BigDecimal(300), BigDecimal(400), BigDecimal(500), BigDecimal(5500), BigDecimal(15000))
        binding.sliderImperative.sliderValue = BigDecimal(200)
    }
}