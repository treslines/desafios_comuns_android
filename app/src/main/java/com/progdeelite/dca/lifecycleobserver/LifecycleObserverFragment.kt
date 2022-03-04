package com.progdeelite.dca.lifecycleobserver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.progdeelite.dca.R
import com.progdeelite.dca.binding.viewBinding
import com.progdeelite.dca.databinding.FragmentLifecycleObserverBinding
import com.progdeelite.dca.util_extension.toast

// 1) COMO OBSERVAR SE O APP ESTA EM BACKGROUND EM FRAGMENTS E SALVAR RECURSOS
// 2) COMO ENTENDER QUE ELE VOLTOU DO BACKGROUND (NÃO É TÃO SIMPLES COMO PENSAMOS)
// 3) COMO RECUPERAR OS VALORES SALVOS E AGIR (CASO SEJA REQUISITO)

class LifecycleObserverFragment : Fragment(R.layout.fragment_lifecycle_observer) {

    // VEJA VIDEO BINDING AUTOMATICO: https://youtu.be/qivrch6qxQw
    private val binding by viewBinding(FragmentLifecycleObserverBinding::bind)
    private var appInBackgroundObserver: AppInBackgroundLifecycleObserver? = null

    companion object {
        private const val APP_IN_BACKGROUND = "APP_IN_BACKGROUND"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // CUIDADO COM MULTIPLOS REGISTROS...
        appInBackgroundObserver = AppInBackgroundLifecycleObserver(outState, ::navegueTelaAnterior).
        also { lifecycle.addObserver(it) }
        outState.putBoolean(APP_IN_BACKGROUND, true)
    }

    private fun navegueTelaAnterior() {
        toast("Navegando de volta")
        //removeLivecycleObserver()
    }

    override fun onDetach() {
        super.onDetach()
        removeLivecycleObserver()
    }

    private fun removeLivecycleObserver() {
        appInBackgroundObserver?.let {
            lifecycle.removeObserver(it)
        }
    }

    class AppInBackgroundLifecycleObserver(val bundle: Bundle?, val action: () -> Unit = {}) :
        DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            if (bundle?.getBoolean(APP_IN_BACKGROUND, false) == true) {
                bundle.putBoolean(APP_IN_BACKGROUND, false)
                action()
            }
        }
    }

    // COLOQUE BREAK POINTS VC MESMO E FACA O TESTE...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
