package com.progdeelite.dca.web_view_force_dark

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentDarkWebViewBinding

// 1) ADICIONAR DEPENDÊNCIA NO build.gradle
// 2) CERTIFIQUE-SE QUE SEU APP TEM UM "Theme" QUE EXTENDE "DayNight"
// 3) USE A FEATURE FORCE-DARK PARA DEIXAR SUA WEBVIEW DARK TAMBEM
class DarkWebViewFragment : Fragment(R.layout.fragment_dark_web_view) {

    private lateinit var binding: FragmentDarkWebViewBinding

    // +--------------------------------------------------------------------------+
    // | https://developer.android.com/reference/androidx/webkit/package-summary  |
    // +--------------------------------------------------------------------------+
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDarkWebViewBinding.bind(view)

        // +-----------------------------------------------------------------+
        // | OUTRA MANEIRA BACANA DE USAR O BINDING EM KOTLIN COM "with"     |
        // +-----------------------------------------------------------------+
        with(binding) {
            val mimeType = "text/html"
            val encoding = "utf-8"
            val htmlText = "<h3>Mude o Tema para Night Em Settings</h3>"
            forceDarkWebview.loadDataWithBaseURL(null, htmlText, mimeType, encoding, null)
            val inDarkMode =
                (requireContext().resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK) && inDarkMode) {
                WebSettingsCompat.setForceDark(
                    forceDarkWebview.settings,
                    WebSettingsCompat.FORCE_DARK_ON
                )
            }
        }
    }
}