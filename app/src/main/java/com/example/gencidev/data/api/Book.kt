package com.example.gencidev.data.api

data class Book(
    val key: String,
    val title: String,
    val author_name: List<String>?,
    val cover_i: Int?
)