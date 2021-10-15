package com.progdeelite.dca.binding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// REFERENCIA OFICIAL
// https://developer.android.com/reference/androidx/lifecycle/DefaultLifecycleObserver

// 1) Definir dependência do build.gradle
// 2) Criar classe inicializa o view binding
// 3) Definir extensão de kotlin para usar em seu projeto/classes
// 4) O que ganhamos com tudo isso afinal?

// +---------------------------------------------------------------------+
// | Classe que se encarrega de realizar o binding em cima de fragments  |
// +---------------------------------------------------------------------+
class FragmentViewBindingDelegate<VB : ViewBinding>(
    val fragment: Fragment,                 // referencia ao seu fragment
    val viewBindingFactory: (View) -> VB    // referncia ao seu metodo de bind da classe ViewBinding
) : ReadOnlyProperty<Fragment, VB> {

    private var binding: VB? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {

            // remove o binding ao destruir o fragment
            val viewLifecycleOwnerLiveDataObserver = Observer<LifecycleOwner?> {
                it?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        binding = null
                    }
                })
            }

            // observa o ciclo de vida ao criar um fragment
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(
                    viewLifecycleOwnerLiveDataObserver
                )
            }

            // remove observer de lifecycle quando fragment é destruido
            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(
                    viewLifecycleOwnerLiveDataObserver
                )
            }
        })
    }

    // metodo que cria efetivamente o binding
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        // se ja existe retorna direto
        binding?.let { return it }

        // so cria se estiver ao menos inicializao
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        // do contrario cria o binding
        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

// +---------------------------------------------------------------------+
// | Extensão "MÃO NA RODA" que facilita inicialização do viewBinding    |
// +---------------------------------------------------------------------+
// Passe "NomeDoXMlBinding::bind" por referencia a este metodo
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)