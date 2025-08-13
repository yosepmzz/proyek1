package com.example.myapplication1

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}