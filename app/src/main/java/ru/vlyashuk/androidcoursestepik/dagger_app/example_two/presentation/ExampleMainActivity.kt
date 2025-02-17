package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.ExampleApp
import javax.inject.Inject

class ExampleMainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ExampleViewModel::class.java]
    }
    private val viewModelTwo by lazy {
        ViewModelProvider(this, viewModelFactory)[ExampleViewModelTwo::class.java]
    }

    private val component by lazy {
        (application as ExampleApp).component
            .activityComponentFactory()
            .create("MY_ID")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_main)
        viewModel.method()
        viewModelTwo.method()
    }
}