package com.progdeelite.dca.onboarding

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentOnboardingBinding
import com.progdeelite.dca.util_extension.getDrawable
import com.progdeelite.dca.util_extension.setVisible
import com.progdeelite.dca.util_extension.toast

// 1) COMO CRIAR O LAYOUT DE UM ONBOARDING SCREEN (USANDO VIEW PAGER 2)
// 2) COMO CRIAR O PROGRESS STEP VIEW (A PEDIDO DE UM DOS SEGUIDORES)
// 3) COMO LIGAR OS PONTOS NO FRAGMENT E MONITORAR O PROGRESSO
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentOnboardingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slideViewPager = binding.fragmentOnboardingSlideViewpager
        // TODO: replace dummy content with real data later on
        val items = listOf(
            IntroSlide(
                R.drawable.onboarding_rotas2,
                R.string.into_slide_title3_onboarding,
                R.string.into_slide_desc3_onboarding
            ),
            IntroSlide(
                R.drawable.onboarding_rotas,
                R.string.into_slide_title2_onboarding,
                R.string.into_slide_desc2_onboarding
            ),
            IntroSlide(
                R.drawable.onboarding_paradas,
                R.string.into_slide_title1_onboarding,
                R.string.into_slide_desc1_onboarding
            ),
            IntroSlide(
                R.drawable.onboarding_linha,
                R.string.into_slide_title4_onboarding,
                R.string.into_slide_desc4_onboarding
            ),
            IntroSlide(
                R.drawable.onboarding_itens,
                R.string.into_slide_title5_onboarding,
                R.string.into_slide_desc5_onboarding
            ),
            IntroSlide(
                R.drawable.onboarding_mapa,
                R.string.into_slide_title6_onboarding,
                R.string.into_slide_desc6_onboarding
            )
        )
        val introSlideAdapter = IntroSlideAdapter(items)
        slideViewPager.adapter = introSlideAdapter
        val indicators = view.findViewById<LinearLayout>(R.id.fragment_onboading_slide_indicator)
        // CRIAR QUANTIDADE CORRETA DE INDICADORES
        setupIndicators(indicators, introSlideAdapter.itemCount)
        // COLOCAR SELEçÃO NO PRIMEIRO ITEM
        setCurrentIndicator(indicators, 0)
        // PARA SABER A HORA DE EXIBIR O BOTÃO FINAL NO ONBOARDING SCREEN
        slideViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(indicators, position)
                if (introSlideAdapter.itemCount - 1 == position) {
                    binding.fragmentOnboardingGo.setVisible(true)
                }
            }
        })

        // QUANDO TOCAR NO BOTÃO, EXECUTE A AçÃO DESEJADA
        binding.fragmentOnboardingGo.setOnClickListener {
            toast("navegue para tela que desejar")
        }
    }

    private fun setupIndicators(indicatorContainer: LinearLayout, indicatorCount: Int) {
        // CRIAR LISTA DE INDICADORES
        val indicators = arrayOfNulls<ImageView>(indicatorCount)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // DIMENSIONAR OS INDICADORES
        val indicatorSpace = resources.getDimensionPixelSize(R.dimen.slide_indicator_space)
        params.setMargins(indicatorSpace, 0, 0, 0)
        // ATRIBUIR BACKGROUND AOS INDICADORES
        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.apply {
                this.setImageDrawable(getDrawable(R.drawable.indicator_unselected))
                this.layoutParams = params
            }
            indicatorContainer.addView(indicators[i])
        }
    }

    // SELECIONAR O INDICADOR DE ACORDO COM A PAGINA ATUAL
    private fun setCurrentIndicator(indicatorContainer: LinearLayout, index: Int) {
        for (i in 0 until indicatorContainer.childCount) {
            val img = indicatorContainer[i] as? ImageView
            when (index == i) {
                true -> img?.setImageDrawable(getDrawable(R.drawable.indicator_selected))
                else -> img?.setImageDrawable(getDrawable(R.drawable.indicator_unselected))
            }
        }
    }
}