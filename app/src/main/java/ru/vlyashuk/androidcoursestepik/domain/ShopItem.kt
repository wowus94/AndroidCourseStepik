package ru.vlyashuk.androidcoursestepik.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}