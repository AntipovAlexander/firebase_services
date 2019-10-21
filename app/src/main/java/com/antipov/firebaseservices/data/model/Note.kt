package com.antipov.firebaseservices.data.model

data class Note(
    val userId: String,
    val title: String,
    val content: String,
    val createdAt: Long
)