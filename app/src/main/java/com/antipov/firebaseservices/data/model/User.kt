package com.antipov.firebaseservices.data.model

data class User(
    private val uid: String,
    private val displayName: String?,
    private val email: String?
)