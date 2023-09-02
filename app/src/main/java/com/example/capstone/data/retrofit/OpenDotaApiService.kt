package com.example.capstone.data.retrofit

import com.example.capstone.data.models.Hero
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.opendota.com/api/"

private val retrofit: Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

interface OpenDotaApiService {

//    @GET("heroes")
    @GET("heroStats")
    suspend fun getHeroes(): List<Hero>

    @GET("constants/item_ids")
    suspend fun getItemIds(): Map<Int, String>

    @GET("heroes/{hero_id}/itemPopularity")
    suspend fun getItemPopularity(@Path("hero_id") heroId: Int): Map<String, Map<Int, Int>>


}

object OpenDotaApi {
    val retrofitService: OpenDotaApiService by lazy {
        retrofit.create(OpenDotaApiService::class.java)
    }
}