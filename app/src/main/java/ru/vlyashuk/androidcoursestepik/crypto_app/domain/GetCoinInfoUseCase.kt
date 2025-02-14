package ru.vlyashuk.androidcoursestepik.crypto_app.domain

class GetCoinInfoUseCase(private val repository: CoinRepository) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}