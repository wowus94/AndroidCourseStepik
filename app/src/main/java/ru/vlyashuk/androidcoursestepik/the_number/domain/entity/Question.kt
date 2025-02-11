package ru.vlyashuk.androidcoursestepik.the_number.domain.entity

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
) {

    val rightAnswer = sum - visibleNumber
}