package com.progdeelite.dca.language

import android.content.Context

const val NO_TRANSLATION_AVAILABLE = "Not translated yet!"
const val SHARED_PREFS_ISO_CODE_KEY = "shared_prefs_iso_code_key"

class LanguageResource(context: Context, selectedLanguageIsoCode: String) {

    private var currentIsoCode = ""
    private val languageResources = mutableMapOf<String, String>()

    companion object AppLanguagesSettings {
        // ... add your language iso codes here...
        private const val ISO_CODE_GERMAN = "de"
        private const val ISO_CODE_FRENCH = "fr"
        private const val ISO_CODE_ENGLISH = "en"
        private const val ISO_CODE_PORTUGUESE = "pt"

        // ... add your literal languages here...
        const val FRENCH = ISO_CODE_FRENCH
        const val GERMAN = ISO_CODE_GERMAN
        const val PORTUGUESE = ISO_CODE_PORTUGUESE
        const val DEFAULT_ENGLISH = ISO_CODE_ENGLISH

        // ... add your available languages here...
        val LANGUAGES_AVAILABLE = listOf(DEFAULT_ENGLISH, PORTUGUESE, FRENCH, GERMAN)
    }

    init {
        loadNewLanguageResource(context, selectedLanguageIsoCode)
    }

    /** idString = Android's R.string.resource_id as a string like this: "R.string.your_resource_id" */
    fun getLangString(idString: String, vararg args: String): String {
        var translation: String = languageResources[idString] ?: ""
        return when {
            languageResources.isEmpty() -> NO_TRANSLATION_AVAILABLE
            args.isNotEmpty() -> {
                args.forEach { arg ->
                    translation = translation.format(arg)
                }
                return translation
            }
            else -> translation
        }
    }

    fun loadNewLanguageResource(context: Context, selectedLanguageIsoCode: String) {
        if (languageResources.isEmpty()) {
            loadResource(selectedLanguageIsoCode, context)
        } else {
            if (selectedLanguageIsoCode != currentIsoCode) {
                loadResource(selectedLanguageIsoCode, context)
            }
        }
    }

    private fun loadResource(selectedIsoCode: String, context: Context) {
        currentIsoCode = selectedIsoCode

        languageResources.clear()
        val code = if (selectedIsoCode == DEFAULT_ENGLISH) "" else "_${selectedIsoCode}"
        val lang = context.resources.getIdentifier("strings$code", "raw", context.packageName)
        val bufferedResource = context.resources.openRawResource(lang).bufferedReader()
        bufferedResource.readLines().forEach { line ->
            when {
                    line.isEmpty() or
                    line.contains("resources ") or
                    !line.contains("<string ") -> {
                    /* do not consider empty lines */
                    /* do not consider file start and end */
                    /* do not consider others than string */
                }
                else -> {
                    val key = "R.string.${line.split("\"")[1]}"
                    val value = line.substring(line.indexOf(">") + 1, line.indexOf("</"))
                    languageResources[key] = value
                }
            }
        }
    }
}