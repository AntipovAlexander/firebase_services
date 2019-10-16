package com.antipov.firebaseservices.domain.user

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthUserUseCase(private val repository: UserRepository) :
    UseCase<Unit, AuthUserUseCase.Params>() {

    override suspend fun invoke(params: Params): Either<Exception, Unit> =
        suspendCoroutine { cont ->
            try {
                repository
                    .authUser(params.login, params.password)
                    .addOnSuccessListener { cont.resume(Either.Right(Unit)) }
                    .addOnFailureListener { e -> cont.resume(Either.Left(e)) }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }

    data class Params(val login: String, val password: String)
}