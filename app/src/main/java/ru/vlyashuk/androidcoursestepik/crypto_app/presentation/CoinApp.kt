package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.app.Application
import androidx.work.Configuration
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.CoinWorkerFactory
import ru.vlyashuk.androidcoursestepik.crypto_app.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var refreshDataWorkerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(refreshDataWorkerFactory)
            .build()
}