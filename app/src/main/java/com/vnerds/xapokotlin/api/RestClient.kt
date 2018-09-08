package com.vnerds.xapokotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {
    val applicationServices: RestServices

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        applicationServices = retrofit.create(RestServices::class.java!!)
    }

    companion object {
        val DOMAIN_URL = "https://api.github.com"
        val BASE_URL = DOMAIN_URL
        val search = "/search/repositories?sort=stars&order=desc&q=language:java&q=created:"
        val get_categories = BASE_URL + "get_categories"
    }
}
