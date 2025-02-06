package ru.vlyashuk.androidcoursestepik.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.vlyashuk.androidcoursestepik.R


class ShopItemViewHolder(val view: View) : ViewHolder(view) {
    val nameTV = view.findViewById<TextView>(R.id.nameTextView)
    val countTV = view.findViewById<TextView>(R.id.countTextView)
}