package com.antipov.firebaseservices.domain

import com.antipov.firebaseservices.Either

class FlatMapData : UseCase<String, FlatMapData.Params>() {

    override suspend fun invoke(params: Params): Either<Exception, String> {
        return try {
            Either.Right("Flatmapped ${params.value}")
        } catch (e: Exception) {
            Either.Left(e)
        }
    }

    // just test stub
    data class Params(val value: String)
}