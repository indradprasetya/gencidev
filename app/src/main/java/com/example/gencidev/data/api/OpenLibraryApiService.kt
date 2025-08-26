package com.example.gencidev.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApiService {
    @GET("search.json")
    suspend fun searchBooks(@Query("q") query: String): ApiResponse
}