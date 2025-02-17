package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di.DaggerApplicationComponent
import javax.inject.Inject

class ExampleMainActivity : AppCompatActivity() {

    private val component by lazy {
        DaggerApplicationComponent.factory()
            .create(application, System.currentTimeMillis())
    }

    @Inject
    lateinit var viewModel: ExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_main)
        viewModel.method()
    }
}