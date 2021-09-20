package com.example.trenirovka_worldskills_1.model

import com.google.gson.annotations.SerializedName


data class ModelProducts(
    @SerializedName("id") val id: Int,
    @SerializedName("name_product") val name_product: String,
    @SerializedName("price") val price: String,
    @SerializedName("text") val text: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("category") val category: HashMap<String,String>,
    @SerializedName("place") val place: HashMap<String,String>
)
