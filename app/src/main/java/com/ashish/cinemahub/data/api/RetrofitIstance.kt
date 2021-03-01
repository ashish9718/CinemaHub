package com.ashish.cinemahub.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitIstance {


    companion object {
        val apiKey= "4b8954791bcfc4bb1574b706e9de68bc"

        val baseurl = "https://api.themoviedb.org/3/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var apiService: ApiService = retrofit.create(ApiService::class.java)
    }
}