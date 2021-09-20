package com.example.trenirovka_worldskills_1.model

import com.google.gson.annotations.SerializedName


data class RegistrationModel(
    @SerializedName("data")
    val data: String,
    @SerializedName("errors")
    val errors: HashMap<String,String>
)