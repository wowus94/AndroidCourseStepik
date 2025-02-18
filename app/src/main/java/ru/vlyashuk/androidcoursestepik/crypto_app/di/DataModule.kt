package ru.vlyashuk.androidcoursestepik.crypto_app.di

import android.app.Application
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.data.network.ApiService
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
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}