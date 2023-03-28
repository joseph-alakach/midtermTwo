package com.example.midterm2



class Datasource {
    suspend fun loadNews(): Results{
        return RetrofitHelper.getInstance().create(ApiService::class.java).getPosts()
    }
}

