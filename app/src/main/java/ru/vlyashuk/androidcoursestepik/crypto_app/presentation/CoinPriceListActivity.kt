package ru.vlyashuk.androidcoursestepik.crypto_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import ru.vlyashuk.androidcoursestepik.MainApp
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinInfo
import ru.vlyashuk.androidcoursestepik.databinding.ActivityCoinPriceListBinding
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as MainApp).component
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
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