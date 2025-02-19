package ru.vlyashuk.androidcoursestepik.factorial_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FactorialViewModel : ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _factorial = MutableLiveData<String>()
    val factorial: LiveData<String>
        get() = _factorial

    fun calculate(value: String?) {
        _progress.value = true
        if (value.isNullOrEmpty()) {
            _progress.value = false
            _error.value = true
            return
        }

        viewModelScope.launch {
            val number = value.toLong()
            delay(1000)
            _progress.value = false
            _factorial.value = number.toString()
        }
    }
}