package com.example.cryptoapp.data.repository


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.vlyashuk.androidcoursestepik.crypto_app.data.database.AppDatabase
import ru.vlyashuk.androidcoursestepik.crypto_app.data.mapper.CoinMapper
import ru.vlyashuk.androidcoursestepik.crypto_app.data.workers.RefreshDataWorker
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinInfo
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinRepository

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }

    override fun loadData() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}
