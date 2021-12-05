package com.progdeelite.dca.mvp.concrete_presenter

import com.progdeelite.dca.mvp.base_alert.AlertAction
import com.progdeelite.dca.mvp.base_alert.AlertContent
import com.progdeelite.dca.mvp.base_alert.AlertType
import com.progdeelite.dca.mvp.concrete_contract.MyViewContract
import com.progdeelite.dca.mvp.navigation.ScreenDestination

class MyViewPresenter : MyViewContract.Presenter {

    private var pView: MyViewContract.View? = null
    // private var seuModelo = MyModel() // OMITIDO PARA FINS DIDÁTICO, PODERIA SER VIEWMODEL, REPOSITORY ETC.

    override fun computeMaxValue(aValue: String, bValue: String): String {
        pView?.hideKeyboardOnButtonClick()
        pView?.showLottieAnim(1) // INSTRUI A VIEW A DISPARAR UMA ANIMAçÃO

        // EXEMPLO DE COMO USAR OS METODOS DE NAVEGAçÃO OU EXIBIçÃO DE ALERTAS PARA FINS DIDÁTICOS
        val result = Math.max(aValue.toInt(), bValue.toInt())
        if(result in 10..20) pView?.navigateTo(ScreenDestination.Home)
        if(result in 0..5) pView?.showAlert(AlertType.SmallScreenSuccess(AlertContent(), AlertAction()))
        return Math.max(aValue.toInt(), bValue.toInt()).toString() // FAZ O CALULO PARA VIEW
    }

    // CHAMDADO PELO BASE FRAGMENT
    override fun attachView(view: MyViewContract.View) {
        pView = view
    }

    // CHAMADO PELO BASE FRAGMENT
    override fun detachView() {
        pView = null
    }

    // PARA USAR DE AC0RDO COM SEU CASO DE USO (AQUI PARA FINS DIDÁTICO, ESTÃO SEM USO)
    override fun onAttached() = Unit
    override fun onDetached() = Unit
    override fun onDestroy() = Unit
}