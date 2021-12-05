package com.progdeelite.dca.mvp.concrete_contract

import com.progdeelite.dca.mvp.base_contract.PresenterContract
import com.progdeelite.dca.mvp.base_contract.ViewContract

// CADA VIEW PRECISA DE UM CONTRATO, ASSIM FICA CLARO O USO DEPOIS
interface MyViewContract {

    interface View : ViewContract {
        // AQUI VC TEM A OPORTUNIDADE DE DEFINIR OS METODOS DO SEU CASO DE USO
        fun showLottieAnim(animId: Int)
        fun hideKeyboardOnButtonClick()
    }

    interface Presenter : PresenterContract<View> {
        // AQUI VC DEFINE OS METODOS DO SEU CASO DE USO
        fun computeMaxValue(aValue: String, bValue: String): String
    }
}