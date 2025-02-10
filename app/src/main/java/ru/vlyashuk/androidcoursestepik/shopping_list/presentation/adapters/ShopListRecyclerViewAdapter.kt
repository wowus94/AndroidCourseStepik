package ru.vlyashuk.androidcoursestepik.shopping_list.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.shopping_list.domain.ShopItem
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.ShopItemDiffCallback
import ru.vlyashuk.androidcoursestepik.shopping_list.presentation.ShopItemViewHolder

class ShopListRecyclerViewAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
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