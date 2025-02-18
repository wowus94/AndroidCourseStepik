package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.network.ApiFactory
import ru.vlyashuk.androidcoursestepik.crypto_app.data.database.AppDatabase
import ru.vlyashuk.androidcoursestepik.crypto_app.data.mapper.CoinMapper
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.RefreshDataWorkerFactory
import ru.vlyashuk.androidcoursestepik.crypto_app.di.DaggerApplicationComponent

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            ).build()
}