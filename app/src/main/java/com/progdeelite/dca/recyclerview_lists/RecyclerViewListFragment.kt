package com.progdeelite.dca.recyclerview_lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentRecyclerViewListBinding
import com.progdeelite.dca.util.navTo

// 1) Refatorar main fragment (xml, + itemAdapter)
// 2) mostrar par vcs como seria facil refazer isso usando recyclerview
// 3) ensinar a voces como escutar ao toque e disparar ações

class RecyclerViewListFragment : Fragment(R.layout.fragment_recycler_view_list) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentRecyclerViewListBinding::bind)

    private val components = mapOf(
        "Api de Resultados" to R.id.setResultFragment,
        "Api de Request" to R.id.requestResultFragment,
        "Esconder Keyboard" to R.id.hideKeyboardFragment,
        "App Sem Internet" to R.id.checkAppInternetFragment,
        "Dark Webview" to R.id.darkWebViewFragment,
        "Check Emulador" to R.id.checkEmulatorFragment,
        "Animar Componentes" to R.id.animFieldsFragment,
        "Back Pressed" to R.id.backPressedFragment,
        "Biometria" to R.id.biometryFragment,
        "Dialog Tela Cheia" to R.id.fullscreenDialogFragment,
        "Polling API" to R.id.pollingApiFragment,
        "Dialog personalizado" to R.id.customDialogFragment,
        "Custom Dialog" to R.id.action_mainFragment_to_customDialogFragment,
        "bottom Sheet" to R.id.bottomSheetDialogFragment,
        "Item Decorators" to R.id.itemDecoratorFragment,
        "Compomente de Login" to R.id.loginFragment,
        "Text Highlighter" to R.id.textHighlighterFragment,
        "Campo de busca" to R.id.searchViewFragment,
        "Restaurar Ações" to R.id.restoreableActionFragment,
        "Status bar dia / noite" to R.id.statusBarDayFragment,
        "Fontes personalizadas" to R.id.customFontFragment,
        "Texto furta-cor" to R.id.textFurtaCorFragment,
        "Navegar entre gráficos" to R.id.mainToSecondGraphFragment,
        "Arquivos 9-Patch" to R.id.ninePatchFragment,
        "Estilos personalizados" to R.id.customStyleFragment,
        "Animação zoom infinito" to R.id.zoomInfiniteFragment,
        "Animação profissional com Lottie" to R.id.lottieFragment,
        "Mudança de orientação" to R.id.orientationFragment,
        "Botões Material Design" to R.id.customButtonFragment,
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.apply {
            adapter = TextItemAdapter(components.keys.toList()) { _: Int, name: String, _: View ->
                components[name]?.let { navTo(it) }
            }
            addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        }
    }

}