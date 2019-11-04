package coder.test.e_commerce.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    // URL
    private const val BASE_URL = "http://devmyanmar.com/api/"

    // OKhttp Client Object
    private val OKHttp: OkHttpClient.Builder = OkHttpClient.Builder()

    // Retrofit Builder Object
    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OKHttp.build())

    // Retrofit Object
    private val retrofit: Retrofit = builder.build()

    // make createService method
    fun <T> createService(st: Class<T>): T {
        return retrofit.create(st)
    }

}