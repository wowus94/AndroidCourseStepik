package ru.vlyashuk.androidcoursestepik.factorial_app

sealed class State

object Error : State()
object Progress : State()
class Factorial(val value: String) : State()