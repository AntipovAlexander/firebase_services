package com.antipov.firebaseservices.domain

import com.antipov.firebaseservices.Either
import kotlinx.coroutines.delay

class GetData : UseCase<String, GetData.Params>() {

    override suspend fun invoke(params: Params): Either<Exception, String> {
        return try {
            delay(1)
            when ((0..1).random()) {
                0 -> Either.Right("Success !")
                1 -> throw RuntimeException("Unlucky :(")
                else -> throw RuntimeException("WTF?")
            }
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    // just test stub
    data class Params(val id: Int)
}