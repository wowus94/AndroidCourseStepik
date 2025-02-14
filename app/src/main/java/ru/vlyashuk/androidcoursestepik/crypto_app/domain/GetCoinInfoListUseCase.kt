package ru.vlyashuk.androidcoursestepik.crypto_app.domain

class GetCoinInfoListUseCase(private val repository: CoinRepository) {

    operator fun invoke() = repository.getCoinInfoList()
}