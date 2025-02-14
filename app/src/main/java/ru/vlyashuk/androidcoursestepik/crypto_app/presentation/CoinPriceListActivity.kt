package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinInfo
import ru.vlyashuk.androidcoursestepik.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                launchCoinDetailFragment(coinPriceInfo.fromSymbol)
            }
        }
        with(binding) {
            rvCoinPriceList.adapter = adapter
            rvCoinPriceList.itemAnimator = null
        }
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun launchCoinDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                CoinDetailFragment.newInstance(fromSymbol)
            )
            .addToBackStack(null)
            .commit()
    }
}