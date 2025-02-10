package ru.vlyashuk.androidcoursestepik.the_number.data

import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.GameSettings
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Level
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Question
import ru.vlyashuk.androidcoursestepik.the_number.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(maxSumValue - countOfOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10, 3, 50, 8
                )
            }

            Level.EASY -> {
                GameSettings(
                    30, 10, 60, 60
                )
            }

            Level.NORMAL -> {
                GameSettings(
                    50, 20, 70, 45
                )
            }

            Level.HARD -> {
                GameSettings(
                    80, 30, 80, 30
                )
            }
        }
    }
}