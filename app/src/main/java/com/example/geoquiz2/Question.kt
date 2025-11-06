package com.example.geoquiz2

data class Question(
    val id: Int,
    val text: String,
    val answer: Boolean,
    val hint: String? = null
)