package ru.vlyashuk.androidcoursestepik.coroutine_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.databinding.ActivityMainCoroutineBinding

class MainCoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCoroutineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loadButton.setOnClickListener {
            binding.progressBar.isVisible = true
            binding.loadButton.isEnabled = false
            val deferredCity: Deferred<String> = lifecycleScope.async {
                val city = loadCity()
                binding.locationTextView.text = city
                city
            }
            val deferredTemp: Deferred<Int> = lifecycleScope.async {
                val temp = loadTemperature()
                binding.temperatureTextView.text = temp.toString()
                temp
            }
            lifecycleScope.launch {
                val city = deferredCity.await()
                val temp = deferredTemp.await()
                Toast.makeText(
                    this@MainCoroutineActivity,
                    "Город: $city Температура: $temp",
                    Toast.LENGTH_SHORT
                ).show()
                binding.progressBar.isVisible = false
                binding.loadButton.isEnabled = true
            }
        }
    }

    private suspend fun loadCity(): String {
        delay(2000)
        return "Белгород"
    }

    private suspend fun loadTemperature(): Int {
        delay(5000)
        return 15
    }
}