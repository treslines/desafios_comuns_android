package com.progdeelite.dca.data_store

import android.content.SharedPreferences
import androidx.core.content.edit

// 1) DEFINIR INTERFACE COMUM (PROJETOS MULTIPLATAFORMA OU COM BASE COMUM)
// 2) CRIAR NOSSA INSTANCIA PARA PERSISTIR CHAVE-VALOR SIMPLES
// 3) CRIAR UMA NOVA INSTANCIA INTERCAMBIÁVEL PARA PERSISTIR CHAVE-VALOR CRIPTOGRAFADA
// 4) ENTENER PORQUE ISSO FACILITA NOSSA VIDA E OS TESTES

// TORNA OS TIPOS DE PERSISTÊNCIAS INTERCAMBIÁVEIS E INJETÁVEIS ATRAVÉS DE KOIN, HILT ETC.
// DEIXA AS CLASSES MAIS FLEXIVÉIS PARA TESTES UNITÁRIOS TAMBEM
interface StorageType {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
    fun deleteString(key: String)
    fun contains(key: String): Boolean
}

// EXEMPLO DE UM ARMAZENAMENTO LOCAL BASEADO EM CHAVE-VALOR SIMPLES
class LocalSimpleStorage(private val preferences: SharedPreferences) : StorageType {

    override fun putString(key: String, value: String) = preferences.edit { putString(key, value) }

    override fun getString(key: String): String = preferences.getString(key, "") ?: ""

    override fun deleteString(key: String) = preferences.edit { remove(key) }

    override fun contains(key: String): Boolean = preferences.contains(key)
}

// EXEMPLO DE UM ARMAZENAMENTO LOCAL BASEADO EM CHAVE-VALOR CRIPTOGRAFADO
// QUE PODERÁ SER USADO NA BIOMETRIA POR EXEMPLO OU OUTROS DADOS SENSÍVEIS
open class LocalSecureStorage(private val preferences: SharedPreferences) : StorageType {
    override fun putString(key: String, value: String) {
        // TODO: INSCREVA-SE PARA NÃO PERDER ESSE VIDEO SUPER ATUAL NOS DIAS DE HOJE
    }

    override fun getString(key: String): String {
        // TODO: INSCREVA-SE PARA NÃO PERDER ESSE VIDEO SUPER ATUAL NOS DIAS DE HOJE
        return ""
    }

    override fun deleteString(key: String) {
        // TODO: INSCREVA-SE PARA NÃO PERDER ESSE VIDEO SUPER ATUAL NOS DIAS DE HOJE
    }

    override fun contains(key: String): Boolean {
        // TODO: INSCREVA-SE PARA NÃO PERDER ESSE VIDEO SUPER ATUAL NOS DIAS DE HOJE
        return true
    }
}