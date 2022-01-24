package com.progdeelite.dca.zoom_infinite

import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentZoomInfiniteBinding

// 1) COMO CRIAR UM ARQUIVO DE ANIMACÃO DE ZOOM INFINITO (PNG, ANIMATION FILE)
// 2) COMO ATRIBUIR A ANIMAçÃO PARA PROMOVER SEUS PRODUTOS
// 3) COMO ALTERAR COR DE PNG USANDO LIGHTING COLOR FILTER NA PRÁTICA

class ZoomInfiniteFragment : Fragment(R.layout.fragment_zoom_infinite) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentZoomInfiniteBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.promoImage.setImageResource(R.drawable.gift)
        animateInfinitely()
    }

    private fun animateInfinitely() {
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in_out_infinity)
        binding.promoImage.startAnimation(anim)

        val drawable: Drawable = binding.promoImage.drawable
        val filter: ColorFilter = LightingColorFilter(
            resources.getColor(R.color.promo_color, null),
            resources.getColor(R.color.promo_color, null)
        )
        drawable.colorFilter = filter
    }
}