package com.progdeelite.dca.map_icon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentMapObjectsBinding
import com.progdeelite.dca.map_icon.CameraScanStatus.*

// 1) COMO CRIAR OBJETOS DE MAPPING
// 2) COMO MAPEAR ESSES OBJETOS COM EXTENSÕES EM KOTLIN
// 3) COMO USAR O MAPPING NA PRÁTICA
class MapObjectsFragment : Fragment(R.layout.fragment_map_objects) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentMapObjectsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            mapAfastese.setOnClickListener {
                ID_MOVE_AWAY.mapIconResId()?.let { drawableId -> mappedImg.setImageResource(drawableId) }
                ID_MOVE_AWAY.mapMessageResId()?.let { textId -> mappedText.text = getString(textId) }
            }
            mapAproximese.setOnClickListener {
                ID_MOVE_CLOSER.mapIconResId()?.let { drawableId -> mappedImg.setImageResource(drawableId) }
                ID_MOVE_CLOSER.mapMessageResId()?.let { textId -> mappedText.text = getString(textId) }
            }
            mapNaoSeMexa.setOnClickListener {
                ID_HOLD_STILL.mapIconResId()?.let { drawableId -> mappedImg.setImageResource(drawableId) }
                ID_HOLD_STILL.mapMessageResId()?.let { textId -> mappedText.text = getString(textId) }
            }
        }
    }
}