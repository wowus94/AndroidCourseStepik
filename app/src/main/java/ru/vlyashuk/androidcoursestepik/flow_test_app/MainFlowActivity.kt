package ru.vlyashuk.androidcoursestepik.flow_test_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vlyashuk.androidcoursestepik.databinding.ActivityMainFlowBinding
import ru.vlyashuk.androidcoursestepik.flow_test_app.team_score.TeamScoreActivity

class MainFlowActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainFlowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }

        binding.buttonCryptoActivity.setOnClickListener {
            startActivity(CryptoActivity.newIntent(this))
        }

        binding.teamScoreActivity.setOnClickListener {
            startActivity(TeamScoreActivity.newIntent(this))
        }
    }
}