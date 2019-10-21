package com.antipov.firebaseservices.data.model

import android.net.Uri

data class User(
    val uid: String,
    val displayName: String?,
    val avatar: Uri?,
    val email: String?,
    val isEmailVerified: Boolean
)