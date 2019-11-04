package coder.test.e_commerce.services

import coder.test.e_commerce.models.Category
import coder.test.e_commerce.models.Product
import coder.test.e_commerce.models.Token
import retrofit2.Call
import retrofit2.http.*


interface WebService {

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("apikey") apikey: String
    ): Call<Token>


    @GET("product_cats")
    fun getAllCat(
        @Header("Authorization") token: String
    ): Call<List<Category>>

    @GET("product/paginate/{start}")
    fun getProductByCatId(
        @Header("Authorization") token: String,
        @Path("start") start: Int
    ): Call<List<Product>>
}