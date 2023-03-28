package com.example.midterm2


import retrofit2.http.GET

interface ApiService {
    @GET("/api/?inc=nat,name,email&results=100")
    suspend fun getPosts(): Results
}