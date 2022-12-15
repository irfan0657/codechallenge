package com.example.codechallenge.data.network

import com.example.codechallenge.data.model.AcromineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebApi {
    /**
     * This function getAcromine(@Query("sf") sf: String?) is calling an api by retrofit to fetch the details of acromine.
     */
    @GET("software/acromine/dictionary.py")
    suspend fun getAcromine(@Query("sf") sf: String?): Response<ArrayList<AcromineResponse.AcromineResponseItem>>
}