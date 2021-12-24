package com.progdeelite.dca.login

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import com.progdeelite.dca.R
import com.progdeelite.dca.databinding.FragmentLoginBinding
import com.progdeelite.dca.language.BaseFragment
import com.progdeelite.dca.language.LanguageResource
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.FRENCH
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.GERMAN
import com.progdeelite.dca.util.*
import com.progdeelite.dca.viewmodel.ExposeObserveViewModel

// 1) Como criar um menu flutuante
// 2) configurações necessãrias no manifest (se fizer certo nem precisa)
// 3) Como permitir entradas em celulares pequenos
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getViewBinding(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    val loginViewModel: ExposeObserveViewModel by viewModels()

    // 1) como interceptar ação de ENTER no teclado virtual
    // 2) Identificar qual tecla foi pressionada, definir extensão
    // 3) Reagir ao toque realizando a ação do seu caso de uso
    override fun initializeUi() {
        with(binding) {
            password.setOnEnterKeyListener(getOnLoginClickedAction())
        }

        // 0) como disparar o evento que altera o modelo e se reflete na Interface
        binding.impressum.setOnClickListener{
            loginViewModel.changeAppName("Novo AppName")
        }
        // 1) como criar viewmodel para expor livedata
        // 2) como export livedata imunatavel no modelo
        // 3) como observar o livedata e atualizar a view automaticamente
        loginViewModel.observableName.observe(viewLifecycleOwner) { newAppName ->
            binding.title.text = newAppName
        }

        // chamar quando oportuno
        startLoading()
        stopLoading()
    }

    private fun getOnLoginClickedAction(): () -> Unit {
        val onClickAction = {
            hideKeyboard()
            login()
        }
        return onClickAction
    }

    private fun login() = toast("logged In")


    // 1) como criar um menu de idiomas na action bar (adicionar à MainAcivity)
    // 2) como exibir os icones dentro do menu (criar layout - faz toda a diferença)
    // 3) como reagir às alterações de idioma na prática
    override fun showActionBarOptionMenu() = true // DETALHE IMPORTANTE

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