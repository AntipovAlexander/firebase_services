package com.antipov.firebaseservices.data.repository.impl

import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.data.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl(private val auth: FirebaseAuth) : UserRepository {

    override fun refreshUserData(): Task<Void> =
        auth.currentUser?.reload() ?: throw RuntimeException("Current user is null.")

    override fun registerUser(login: String, password: String) =
        auth.createUserWithEmailAndPassword(login, password)

    override fun authUser(login: String, password: String) =
        auth.signInWithEmailAndPassword(login, password)

    override fun getCurrentUser() =
        auth.currentUser?.let { User(it.uid, it.displayName, it.photoUrl, it.email, it.isEmailVerified) }

    override fun validateEmail() =
        auth.currentUser?.sendEmailVerification() ?: throw RuntimeException("Current user is null.")
}