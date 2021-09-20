package com.example.trenirovka_worldskills_1.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlacesModel(
    @SerializedName("id") var id: Int,
    @SerializedName("place_name") var place_name: String,
    @SerializedName("place_photo") var place_photo: String,
    @SerializedName("place_open") var place_open: String,
    @SerializedName("place_close") var place_close: String,
    @SerializedName("operating_mode") var operating_mode: Boolean
) : Serializable
