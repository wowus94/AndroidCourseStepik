package ru.vlyashuk.androidcoursestepik.crypto_app.domain

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.loadData()
}
