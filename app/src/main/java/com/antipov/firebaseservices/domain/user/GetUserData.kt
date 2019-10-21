package com.antipov.firebaseservices.domain.user

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.model.User
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetUserData(private val userRepository: UserRepository) : UseCase<User, Any?>() {
    override suspend fun invoke(params: Any?): Either<Exception, User> =
        suspendCoroutine { cont ->
            try {
                userRepository.refreshUserData().addOnSuccessListener {
                    userRepository.getCurrentUser()?.let {
                        cont.resume(Either.Right(it))
                    } ?: throw RuntimeException("User is null")
                }.addOnCanceledListener { throw RuntimeException("Action is cancelled") }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }
}