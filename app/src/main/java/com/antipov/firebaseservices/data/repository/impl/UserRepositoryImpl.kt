package com.antipov.firebaseservices.data.repository.impl

import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl(private val auth: FirebaseAuth) : UserRepository {
    override fun getCurrentUser(): User? {
        return auth.currentUser?.let {
            User(it.uid, it.displayName, it.email)
        }
    }
}