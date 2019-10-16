package com.antipov.firebaseservices.data.repository

import com.antipov.firebaseservices.data.model.User

interface UserRepository {
    fun getCurrentUser() : User?
}