package ru.vlyashuk.androidcoursestepik.coroutine_app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.ActivityMainCoroutineBinding

class MainCoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainCoroutineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCoroutineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loadButton.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        Log.d("MainActivity", "Load started: $this")
        binding.progressBar.isVisible = true
        binding.loadButton.isEnabled = false
        val city = loadCity()
        binding.locationTextView.text = city
        val temp = loadTemperature(city)
        binding.temperatureTextView.text = temp.toString()
        binding.progressBar.isVisible = false
        binding.loadButton.isEnabled = true
        Log.d("MainActivity", "Load finished: $this")
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return "Belgorod"
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            getString(R.string.loading_temperature_toast, city),
            Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 15
    }
}