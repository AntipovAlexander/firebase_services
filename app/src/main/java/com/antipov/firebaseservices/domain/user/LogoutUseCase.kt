package com.antipov.firebaseservices.domain.user

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LogoutUseCase(private val repository: UserRepository) :
    UseCase<Unit, Unit>() {

    override suspend fun invoke(params: Unit): Either<Exception, Unit> {
        return try {
            repository.logout()
            Either.Right(Unit)
        } catch (e: Exception) {
            Either.Left(e)
        }
    }
}