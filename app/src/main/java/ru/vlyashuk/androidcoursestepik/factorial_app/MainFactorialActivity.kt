package ru.vlyashuk.androidcoursestepik.factorial_app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.vlyashuk.androidcoursestepik.databinding.ActivityMainFactorialBinding

class MainFactorialActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainFactorialBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[FactorialViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.editTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.progress.observe(this) {
            if (it) {
                with(binding) {
                    progressBarLoading.visibility = View.VISIBLE
                    buttonCalculate.isEnabled = false
                }
            } else {
                with(binding) {
                    progressBarLoading.visibility = View.GONE
                    buttonCalculate.isEnabled = true
                }
            }
        }
        viewModel.error.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    "Введите число",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.factorial.observe(this) {
            binding.textViewFactorial.text = it
        }
    }
}