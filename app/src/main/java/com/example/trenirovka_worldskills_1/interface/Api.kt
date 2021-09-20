package com.example.trenirovka_worldskills_1.`interface`

import com.example.trenirovka_worldskills_1.model.LoginModel
import com.example.trenirovka_worldskills_1.model.ModelProducts
import com.example.trenirovka_worldskills_1.model.PlacesModel
import com.example.trenirovka_worldskills_1.model.RegistrationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("register")
    fun registration(
        @Field("firstname") firstname: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegistrationModel>


    @FormUrlEncoded
    @POST("resetPassword")
    fun resetPassword(@Field("email") email: String): Call<Object>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginModel>


    @GET("places")
    fun places(): Call<ArrayList<PlacesModel>>

    @GET("categorys/{placeId}")
    fun categorys_placeId(@Path("placeId") placeId: String): Call<ArrayList<HashMap<String, String>>>

    @GET("products/{placeId}")
    fun products_placeId(@Path("placeId") placeId: Int): Call<ArrayList<ModelProducts>>


    @GET("products/{placeId}/{categoryId}x")
    fun products_placeId_categoryId(@Path("placeId") placeId : Int , @Path("categoryId") categoryId : Int) : Call<ArrayList<ModelProducts>>
}


