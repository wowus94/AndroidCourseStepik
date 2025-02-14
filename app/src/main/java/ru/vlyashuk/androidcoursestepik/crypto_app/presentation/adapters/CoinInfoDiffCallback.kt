package ru.vlyashuk.androidcoursestepik.crypto_app.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.vlyashuk.androidcoursestepik.crypto_app.domain.CoinInfo

object CoinInfoDiffCallback : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}
