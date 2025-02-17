package ru.vlyashuk.androidcoursestepik.crypto_app.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vlyashuk.androidcoursestepik.crypto_app.data.database.AppDatabase
import ru.vlyashuk.androidcoursestepik.crypto_app.data.database.CoinInfoDao
import ru.vlyashuk.androidcoursestepik.crypto_app.data.repository.CoinRepositoryImpl
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}