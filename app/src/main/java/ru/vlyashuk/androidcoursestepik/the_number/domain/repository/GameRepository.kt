package ru.vlyashuk.androidcoursestepik.the_number.domain.repository

import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.GameSettings
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Level
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}