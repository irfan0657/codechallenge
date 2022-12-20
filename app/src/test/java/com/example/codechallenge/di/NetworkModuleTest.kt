package com.example.codechallenge.di

import com.example.codechallenge.data.network.WebApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkModuleTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var webApi: WebApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        webApi = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WebApi::class.java)
    }

    @Test
    fun testgetAcromine_returnAcromine() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)
        val response = webApi.getAcromine("mmm")
        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}