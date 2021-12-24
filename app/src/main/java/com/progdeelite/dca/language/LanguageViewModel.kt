package com.progdeelite.dca.language
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.DEFAULT_ENGLISH
import com.progdeelite.dca.language.LanguageResource.AppLanguagesSettings.LANGUAGES_AVAILABLE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * SAMPLE ON HOW TO USE IT:
 * In your abstract BaseFragment or BaseActivity add this snippet bellow:
 *
 *
 * override fun onResume() {
 *  super.onResume()
 *  translationModel.translate()
 * }
 *
 * override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *  super.onViewCreated(view, savedInstanceState)
 *  translationModel.observableLanguageResource.observe(viewLifecycleOwner, { translateUi(it) })
 *  translationModel.initAppLanguage(requireContext(),requireContext().getSharedPrefs())
 * }
 *
 * protected abstract fun translateUi(resource: LanguageResource)
 *
 * @author Ricardo Ferreira
 * @since 1.0.0 - 18.12.2021
 */
class LanguageViewModel : ViewModel() {

    private val _observableLanguageResource: MutableLiveData<LanguageResource> = MutableLiveData()
    private var languageResource: LanguageResource? = null

    /** every view should observe this language resource while defining its ui elements */
    val observableLanguageResource: LiveData<LanguageResource> = _observableLanguageResource

    /** Use this method to set/change the App's language over the UI language selector */
    fun setAppLanguage(context: Context, selectedLanguage: String, sharedPres: SharedPreferences) {
        when (selectedLanguage) {
            !in LANGUAGES_AVAILABLE -> changeAppLanguage(context, DEFAULT_ENGLISH, sharedPres)
            else -> changeAppLanguage(context, selectedLanguage, sharedPres)
        }
    }

    /**
     * Call it onViewCreated() from your Base Activity or Fragments
     * after observing this resource - see snippet above
     */
    fun initAppLanguage(context: Context, sharedPres: SharedPreferences){
        setAppLanguage(context, getCurrentAppLanguage(sharedPres), sharedPres)
    }

    /** Call it onResume() from your base activity or fragment classes - see snippet above */
    fun translate() = languageResource?.let { notifyObservers(it) }

    private fun changeAppLanguage(
        context: Context,
        selectedLanguageIsoCode: String,
        sharedPres: SharedPreferences
    ) {
        if (getCurrentAppLanguage(sharedPres) != selectedLanguageIsoCode || languageResource == null) {
            setCurrentAppLanguage(selectedLanguageIsoCode, sharedPres)
            viewModelScope.launch(Dispatchers.IO) {
                if (languageResource != null) {
                    languageResource!!.loadNewLanguageResource(context, selectedLanguageIsoCode)
                    notifyObservers(languageResource!!)
                } else {
                    languageResource = LanguageResource(context, selectedLanguageIsoCode)
                    notifyObservers(LanguageResource(context, selectedLanguageIsoCode))
                }
            }
        }
    }

    private fun notifyObservers(newLanguageResource: LanguageResource) {
        _observableLanguageResource.postValue(newLanguageResource)
    }

    private fun setCurrentAppLanguage(
        languageIsoCode: String,
        sharedPres: SharedPreferences
    ) {
        when {
            languageIsoCode.isNotEmpty() && languageIsoCode in LANGUAGES_AVAILABLE -> {
                sharedPres.edit().putString(SHARED_PREFS_ISO_CODE_KEY, languageIsoCode).apply()
            }
            else -> sharedPres.edit().putString(SHARED_PREFS_ISO_CODE_KEY, DEFAULT_ENGLISH)
                .apply()
        }
    }

    private fun getCurrentAppLanguage(langSharedPres: SharedPreferences): String {
        return langSharedPres.getString(SHARED_PREFS_ISO_CODE_KEY, DEFAULT_ENGLISH)
            ?: DEFAULT_ENGLISH
    }
}