package ru.vlyashuk.androidcoursestepik.factorial_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FactorialViewModel : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state


    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrEmpty()) {
            _state.value = Error
            return
        }

        viewModelScope.launch {
            val number = value.toLong()
            delay(1000)
            _state.value = Result(number.toString())
        }
    }
}