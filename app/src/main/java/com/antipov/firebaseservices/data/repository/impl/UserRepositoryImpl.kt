package com.antipov.firebaseservices.data.repository.impl

import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl(private val auth: FirebaseAuth) : UserRepository {
    override fun registerUser(login: String, password: String) =
        auth.createUserWithEmailAndPassword(login, password)

    override fun authUser(login: String, password: String) =
        auth.signInWithEmailAndPassword(login, password)

    override fun getCurrentUser() =
        auth.currentUser?.let { User(it.uid, it.displayName, it.email) }
}