package ru.vlyashuk.androidcoursestepik.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.domain.ShopItem

class ShopListRecyclerViewAdapter :
    RecyclerView.Adapter<ShopListRecyclerViewAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    class ShopItemViewHolder(val view: View) : ViewHolder(view) {
        val nameTV = view.findViewById<TextView>(R.id.nameTextView)
        val countTV = view.findViewById<TextView>(R.id.countTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknow view type $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        holder.apply {
            nameTV.text = shopItem.name
            countTV.text = shopItem.count.toString()

            view.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }

            view.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 15
    }

}