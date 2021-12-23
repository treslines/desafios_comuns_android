package com.progdeelite.dca.login

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentLoginBinding
import com.progdeelite.dca.language.BaseFragment
import com.progdeelite.dca.language.LanguageResource
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.FRENCH
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.GERMAN
import com.progdeelite.dca.util.*

// 1) Como criar um menu flutuante
// 2) configurações necessãrias no manifest (se fizer certo nem precisa)
// 3) Como permitir entradas em celulares pequenos

class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
    override fun showActionBarOptionMenu() = true

    // 1) como interceptar ação de ENTER no teclado virtual
    // 2) Identificar qual tecla foi pressionada, definir extensão
    // 3) Reagir ao toque realizando a ação do seu caso de uso
    override fun initializeUi() {
        with(binding) {
            password.setOnEnterKeyListener(getOnLoginClickedAction())
        }
    }

    private fun getOnLoginClickedAction(): () -> Unit {
        val onClickAction = {
            hideKeyboard()
            login()
        }
        return onClickAction
    }

    private fun login() = toast("logged In")

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.language_settings, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fr -> {
                setLanguage(FRENCH)
                return true
            }
            R.id.action_de -> {
                setLanguage(GERMAN)
                return true
            }
            else -> Unit
        }
        return super.onOptionsItemSelected(item)
    }

    override fun translateUi(resource: LanguageResource) {
        //translate(resource)
        animateTranslation(resource)
    }

    private fun animateTranslation(resource: LanguageResource){
        with(binding){
            title.animateTranslation(resource.getLangString("R.string.app_login_title", ""))
            usernameLayout.animateTranslation(resource.getLangString("R.string.username_hint"))
            passwordLayout.animateTranslation(resource.getLangString("R.string.password_hint"))
            loginButton.animateTranslation(resource.getLangString("R.string.loggin"))
            impressum.animateTranslation(resource.getLangString("R.string.imprint"))
        }
    }

    private fun translate(resource: LanguageResource) {
        with(binding) {
            title.animateTranslation(resource.getLangString("R.string.app_login_title", ""))
            usernameLayout.animateTranslation(resource.getLangString("R.string.username_hint"))
            passwordLayout.animateTranslation(resource.getLangString("R.string.password_hint"))
            loginButton.animateTranslation(resource.getLangString("R.string.loggin"))
            impressum.animateTranslation(resource.getLangString("R.string.imprint"))
        }
    }
}