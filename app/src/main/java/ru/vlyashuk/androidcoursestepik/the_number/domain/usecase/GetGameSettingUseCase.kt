package ru.vlyashuk.androidcoursestepik.the_number.domain.usecase

import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.GameSettings
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Level
import ru.vlyashuk.androidcoursestepik.the_number.domain.repository.GameRepository

class GetGameSettingUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}