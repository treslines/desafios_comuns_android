package com.progdeelite.dca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 1) Como expor livedata em viewmodels
// 2) como observar os dados do livedata
// 3) como atualizar os dados do livedata
// 4) Como usar livedata para atualizar as views
class ExposeObserveViewModel : ViewModel() {
    // FONTE UNICA DA VERDADE - SINGLE SOURCE OF TRUTH
    private val _observableName: MutableLiveData<String> = MutableLiveData()

    /** every view should observe this Name while defining its ui elements */
    val observableName: LiveData<String> = _observableName // IMUTÁVEL

    /** Use this method to set/change the App's Name over the UI selector */
    fun changeAppName(newName: String) {
        when {
            _observableName.value != newName -> notifyObservers(newName)
            else -> Unit
        }
    }
    private fun notifyObservers(newName: String) = _observableName.postValue(newName)
}