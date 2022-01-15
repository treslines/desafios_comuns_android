package com.progdeelite.dca.statusbar

import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentStatusBarDayBinding
import com.progdeelite.dca.language.BaseFragment
import com.progdeelite.dca.language.LanguageResource
import com.progdeelite.dca.util.navTo

// VEJA VIDEO COMO CRIAR UMA CLASSE BASE FRAGMENTS: https://youtu.be/ycTBkqMK9Pg
class StatusBarDayFragment : BaseFragment<FragmentStatusBarDayBinding>() {

    // VEJA VIDEO COMO ALTERAR IDIOMA SEM RE-INICIAR APP
    // (MODEL-VIEW-LANGUAGE): https://youtu.be/U4VnX44IolE
    override fun translateUi(resource: LanguageResource) {
        // NOPE
    }

    override fun getViewBinding(): FragmentStatusBarDayBinding {
        return FragmentStatusBarDayBinding.inflate(layoutInflater)
    }

    override fun initializeUi() {
        binding.nextButton.setOnClickListener { navTo(R.id.statusBarNightFragment) }
    }
}