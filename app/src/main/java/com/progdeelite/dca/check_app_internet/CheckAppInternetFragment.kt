package com.progdeelite.dca.check_app_internet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentCheckAppInternetBinding
import com.progdeelite.dca.util.hasInternet
import com.progdeelite.dca.util.toast

class CheckAppInternetFragment : Fragment(R.layout.fragment_check_app_internet) {
    private lateinit var binding: FragmentCheckAppInternetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckAppInternetBinding.bind(view)

        binding.appHasInternetButton.setOnClickListener {
            if (hasInternet()) {
                toast("Tem acesso a intenet! TOP!")
            } else {
                toast("App NÃO tem acesso a intenet! ;(")
            }
        }
    }
}