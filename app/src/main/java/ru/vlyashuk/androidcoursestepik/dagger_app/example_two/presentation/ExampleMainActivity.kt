package ru.vlyashuk.androidcoursestepik.dagger_app.example_two.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.dagger_app.example_two.di.DaggerApplicationComponent
import javax.inject.Inject

class ExampleMainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ExampleViewModel

    private val component = DaggerApplicationComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_main)
        viewModel.method()
    }
}