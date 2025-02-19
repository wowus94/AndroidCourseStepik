package ru.vlyashuk.androidcoursestepik.coroutine_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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
            val jobCity = lifecycleScope.launch {
                val city = loadCity()
                binding.locationTextView.text = city
            }
            val jobTemp = lifecycleScope.launch {
                val temp = loadTemperature()
                binding.temperatureTextView.text = temp.toString()
            }
            lifecycleScope.launch {
                jobCity.join()
                jobTemp.join()
                binding.progressBar.isVisible = false
                binding.loadButton.isEnabled = true
            }
        }
    }

    private suspend fun loadCity(): String {
        delay(2000)
        return "Belgorod"
    }

    private suspend fun loadTemperature(): Int {
        delay(5000)
        return 15
    }
}