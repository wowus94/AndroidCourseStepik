package ru.vlyashuk.androidcoursestepik.the_number.domain.usecase

import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Question
import ru.vlyashuk.androidcoursestepik.the_number.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}