package ru.vlyashuk.androidcoursestepik.flow_test_app.team_score

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.ActivityTeamScoreBinding

class TeamScoreActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTeamScoreBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TeamScoreViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        setupListeners()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    when (it) {
                        is TeamScoreState.Game -> {
                            binding.team1Score.text = it.score1.toString()
                            binding.team2Score.text = it.score2.toString()
                        }

                        is TeamScoreState.Winner -> {
                            binding.team1Score.text = it.score1.toString()
                            binding.team2Score.text = it.score2.toString()
                            Toast.makeText(
                                this@TeamScoreActivity,
                                "Winner: ${it.winnerTeam}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.team1Logo.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_1)
        }
        binding.team2Logo.setOnClickListener {
            viewModel.increaseScore(Team.TEAM_2)
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, TeamScoreActivity::class.java)
    }
}