package com.vnerds.xapokotlin.api

import com.vnerds.xapokotlin.objects.Test
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RestServices {

    @GET
    fun getTopRepository(@Url url: String): Call<Test>

}
