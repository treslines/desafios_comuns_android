package com.progdeelite.dca.mvp.base_view

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.progdeelite.dca.mvp.base_alert.AlertType
import com.progdeelite.dca.mvp.base_contract.PresenterContract
import com.progdeelite.dca.mvp.base_contract.ViewContract
import com.progdeelite.dca.mvp.navigation.ScreenDestination

// VIEW BASE QUE JA ABSTRAI ALGUMAS COISAS, CONTEM REFERENCIA AO PRESENTER E IMPLEMENTA VIEW CONTRACT
abstract class BaseFragmentView<P : PresenterContract<*>>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId), ViewContract {

    abstract fun getPresenter(): PresenterContract<ViewContract>

    override fun onStart() {
        super.onStart()
        getPresenter().attachView(this) // ASSIM NÃO ESQUECEMOS DE FAZER O ATTACH
    }

    override fun onStop() {
        super.onStop()
        getPresenter().detachView() // ASSIM NÃO ESQUECEMOS DE FAZER O DETACH
    }

    // IMPLEMENTE DO JEITO QUE SEU USE CASE NECESSITAR
    override fun showProgress() = Unit
    override fun hideProgress() = Unit
    override fun close(animated: Boolean) = Unit
    override fun goBack(animated: Boolean) = Unit

    // 1) COMO CRIAR ALERTAS PADRÃO PARA USAR NO MVP (AlertType)
    // 2) COMO DEFINIR DESTINACÕES DE NAVEGAçÃO (ScreenDestination)
    // 3) COMO USAR ISSO NA PRÁTICA (MyView)
    override fun showAlert(type: AlertType) = Unit
    override fun showModalAlert(type: AlertType) = Unit
    override fun navigateTo(screen: ScreenDestination, animated: Boolean) = Unit
}