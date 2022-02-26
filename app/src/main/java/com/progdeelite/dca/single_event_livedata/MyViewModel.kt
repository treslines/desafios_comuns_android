package com.progdeelite.dca.single_event_livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    private val _observableNormalEvent: MutableLiveData<String> = MutableLiveData()
    private val _observableSingleEvent: SingleEventLiveData<String> = SingleEventLiveData()

    val observableNormalEvent: LiveData<String> = _observableNormalEvent
    val observableSingleEvent: SingleEventLiveData<String> = _observableSingleEvent

    /** Use this method to set/change the simple event */
    fun changeNormalEvent(normalEvent: String) {
        when {
            _observableNormalEvent.value != normalEvent -> notifyNormalObservers(normalEvent)
            else -> Unit
        }
    }
    private fun notifyNormalObservers(newName: String) = _observableNormalEvent.postValue(newName)

    fun changeSingleEvent(singleEvent: String) {
        when {
            _observableSingleEvent.value != singleEvent -> notifySingleObservers(singleEvent)
            else -> Unit
        }
    }
    private fun notifySingleObservers(newName: String) = _observableSingleEvent.postValue(newName)
}