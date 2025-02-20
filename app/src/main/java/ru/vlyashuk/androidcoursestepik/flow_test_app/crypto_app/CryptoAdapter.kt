package ru.vlyashuk.androidcoursestepik.flow_test_app.crypto_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.CryptoItemBinding

class CryptoAdapter : ListAdapter<Currency, CryptoAdapter.CryptoViewHolder>(CryptoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val context = holder.binding.root.context
        val currency = getItem(position)
        holder.binding.textViewCurrencyName.text = currency.name
        holder.binding.textViewCurrencyPrice.text = context.getString(
            R.string.currency_price,
            "${currency.price}"
        )
    }

    class CryptoViewHolder(val binding: CryptoItemBinding) : RecyclerView.ViewHolder(binding.root)
}

private object CryptoDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}