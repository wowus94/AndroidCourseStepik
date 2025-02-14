package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.GetCoinInfoListUseCase
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.GetCoinInfoUseCase
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}
