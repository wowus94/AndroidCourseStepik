package ru.vlyashuk.androidcoursestepik.dagger_app.example_one

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.R

class MainDaggerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dagger)
        val activity = Activity()
        activity.mouse.toString()
        activity.monitor.toString()
        activity.keyboard.toString()
    }
}