package ru.vlyashuk.androidcoursestepik.crypto_app.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameDto (
    @Expose
    @SerializedName("Name")
    val name: String? = null
)