package com.antipov.firebaseservices.data.repository

import com.antipov.firebaseservices.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface UserRepository {
    fun getCurrentUser() : User?
    fun authUser(login: String, password: String): Task<AuthResult>
}