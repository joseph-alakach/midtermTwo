package com.example.midterm2

data class Post(
    val name: Name,
    val email: String,
    val nat: String
)

data class Results(
    val results: List<Post>
)

data class Name(
    val title: String,
    val first: String,
    val last: String
)