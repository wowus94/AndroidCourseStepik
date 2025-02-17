package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.GetCoinInfoListUseCase
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.GetCoinInfoUseCase
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }
}
