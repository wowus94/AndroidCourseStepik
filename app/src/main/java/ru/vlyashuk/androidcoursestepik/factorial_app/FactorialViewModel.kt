package ru.vlyashuk.androidcoursestepik.factorial_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FactorialViewModel : ViewModel() {

    private val coroutineScope =
        CoroutineScope(Dispatchers.Default + CoroutineName("Test coroutine scope"))

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state


    fun calculate(value: String?) {
        _state.value = Progress
        if (value.isNullOrEmpty()) {
            _state.value = Error
            return
        }

        coroutineScope.launch(Dispatchers.Main) {
            val number = value.toLong()
            val result = withContext(Dispatchers.Default) {
                factorial(number)
            }
            _state.value = Factorial(result)
        }
    }

    private fun factorial(number: Long): String {
        var result = BigInteger.ONE
        for (i in 1..number) {
            result = result.multiply(BigInteger.valueOf(i))
        }
        return result.toString()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

}