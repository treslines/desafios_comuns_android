package com.progdeelite.dca.hide_keyboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentHideKeyboardBinding
import com.progdeelite.dca.util_extension.hideKeyboard

class HideKeyboardFragment : Fragment(R.layout.fragment_hide_keyboard) {
    private lateinit var binding: FragmentHideKeyboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHideKeyboardBinding.bind(view)

        binding.hideKeyboardButton.setOnClickListener { hideKeyboard() }
    }
}