package com.progdeelite.dca.mvp.base_contract

import com.progdeelite.dca.mvp.base_alert.AlertType
import com.progdeelite.dca.mvp.navigation.ScreenDestination

// 1) DEFINIR CONTRATOS PARA O PADRÃO MVP (UML-DIAGRAM)
// 2) CRIAR ESTRUTURA DE FRAGMENT BASE PARAS VIEWS
// 3) ESPECIFICAR CONTRATOS - PRESENTER E VIEW PARA CASO DE USO
// 4) IMPLEMENTAR O PRESENTER E A VIEW (FRAGMENT) REFERÊNCIA

interface NavigationContract {
    fun close(animated: Boolean = true)
    fun goBack(animated: Boolean = true)

    // NAVEGAçÃO NO MVP
    fun navigateTo(screen: ScreenDestination, animated: Boolean = true)
}

interface ViewContract : NavigationContract {
    fun showProgress()
    fun hideProgress()

    // EXIBIR ALERTAS
    fun showAlert(type: AlertType)
    fun showModalAlert(type: AlertType)
}

interface PresenterContract<T : ViewContract> {
    fun attachView(view: T) // use it on BaseFragment to automate the attach process
    fun detachView()        // use it on BaseFragment to automate the detach process
    fun onAttached() // call this on attached from your view, whenever you need to do something on this event
    fun onDetached() // call this on attached from your view, whenever you need to do something on this event
    fun onDestroy()  // call this on attached from your view, whenever you need to do something on this event
}