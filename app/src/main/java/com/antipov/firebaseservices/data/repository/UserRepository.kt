package com.antipov.firebaseservices.data.repository

import com.antipov.firebaseservices.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    fun getCurrentUser() : User?
    fun authUser(login: String, password: String): Task<AuthResult>
    fun registerUser(login: String, password: String): Task<AuthResult>
    fun refreshUserData(): Task<Void>
}