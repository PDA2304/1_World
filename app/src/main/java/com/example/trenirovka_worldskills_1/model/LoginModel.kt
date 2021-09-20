package com.example.trenirovka_worldskills_1.model

import com.google.gson.annotations.SerializedName
import java.lang.Error

data class LoginModel(
    @SerializedName("data") val data: String,
    @SerializedName("errors") val error: HashMap<String, String>
)
