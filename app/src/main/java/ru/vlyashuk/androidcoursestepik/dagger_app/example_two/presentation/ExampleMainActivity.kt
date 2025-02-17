package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.ExampleApp
import javax.inject.Inject

class ExampleMainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ExampleViewModel

    private val component by lazy {
        (application as ExampleApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_main)
        viewModel.method()
    }
}