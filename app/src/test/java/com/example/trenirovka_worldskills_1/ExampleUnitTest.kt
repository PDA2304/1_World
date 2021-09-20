package com.example.trenirovka_worldskills_1

import com.example.trenirovka_worldskills_1.`interface`.Api
import com.example.trenirovka_worldskills_1.model.PlacesModel
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
       assertNull( Retrofit.Builder().baseUrl("https://eda.ucmpt.ru/api/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
            .places().execute().body())
    }
}