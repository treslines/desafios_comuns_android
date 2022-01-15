package com.progdeelite.dca.statusbar

import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentStatusBarNightBinding
import com.progdeelite.dca.language.BaseFragment
import com.progdeelite.dca.language.LanguageResource
import com.progdeelite.dca.util.navTo

// VEJA VIDEO COMO CRIAR UMA CLASSE BASE FRAGMENTS: https://youtu.be/ycTBkqMK9Pg
class StatusBarNightFragment : BaseFragment<FragmentStatusBarNightBinding>() {

    // VEJA VIDEO COMO ALTERAR IDIOMA SEM RE-INICIAR APP
    // (MODEL-VIEW-LANGUAGE): https://youtu.be/U4VnX44IolE
    override fun translateUi(resource: LanguageResource) {
        // NOPE
    }

    override fun getViewBinding(): FragmentStatusBarNightBinding {
        return FragmentStatusBarNightBinding.inflate(layoutInflater)
    }

    override fun initializeUi() {
        binding.backButton.setOnClickListener { navTo(R.id.statusBarDayFragment) }
    }

    // ALTERAR BARRA DE STATUS
    override fun hasInvertedStatusBar() = true
}