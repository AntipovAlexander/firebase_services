package com.antipov.firebaseservices.domain.user

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ValidateEmailUseCase(private val repository: UserRepository) :
    UseCase<Unit, Any?>() {

    override suspend fun invoke(params: Any?): Either<Exception, Unit> =
        suspendCoroutine { cont ->
            try {
                repository
                    .validateEmail()
                    .addOnSuccessListener {
                        cont.resume(Either.Right(Unit))
                    }.addOnCanceledListener { throw RuntimeException("Verification is cancelled") }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }
}