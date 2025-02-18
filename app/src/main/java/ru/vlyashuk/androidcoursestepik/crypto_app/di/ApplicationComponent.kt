package ru.vlyashuk.androidcoursestepik.crypto_app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.vlyashuk.androidcoursestepik.crypto_app.presentation.CoinApp
import ru.vlyashuk.androidcoursestepik.crypto_app.presentation.CoinDetailFragment
import ru.vlyashuk.androidcoursestepik.crypto_app.presentation.CoinPriceListActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}