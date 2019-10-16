package com.antipov.firebaseservices.domain.user

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase

class IsUserLogged(private val userRepository: UserRepository) : UseCase<Boolean, Any?>() {
    override suspend fun invoke(params: Any?): Either<Exception, Boolean> {
        return try {
            Either.Right(userRepository.getCurrentUser() != null)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}