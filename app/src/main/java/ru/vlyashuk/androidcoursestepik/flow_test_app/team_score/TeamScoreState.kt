package ru.vlyashuk.androidcoursestepik.flow_test_app.team_score

sealed class TeamScoreState {
    data class Game(
        val score1: Int,
        val score2: Int
    ) : TeamScoreState()

    data class Winner(
        val winnerTeam: Team,
        val score1: Int,
        val score2: Int
    ) : TeamScoreState()
}