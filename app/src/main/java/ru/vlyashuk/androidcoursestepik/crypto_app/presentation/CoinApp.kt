package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.app.Application
import ru.vlyashuk.androidcoursestepik.crypto_app.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}