package ru.vlyashuk.androidcoursestepik

import android.app.Application
import androidx.work.Configuration
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.CoinWorkerFactory
import ru.vlyashuk.androidcoursestepik.crypto_app.di.DaggerApplicationComponent
import javax.inject.Inject

class MainApp : Application(), Configuration.Provider {

    @Inject
    lateinit var refreshDataWorkerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    val componentShoppingList by lazy {
        ru.vlyashuk.androidcoursestepik.shopping_list.di.DaggerApplicationComponent.factory()
            .create(this)
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