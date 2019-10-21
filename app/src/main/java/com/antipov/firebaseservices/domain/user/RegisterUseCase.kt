package com.antipov.firebaseservices.domain.user

import android.net.Uri
import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.domain.base.UseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RegisterUseCase(private val repository: UserRepository) :
    UseCase<Unit, RegisterUseCase.Params>() {

    override suspend fun invoke(params: Params): Either<Exception, Unit> =
        suspendCoroutine { cont ->
            try {
                repository
                    .registerUser(params.login, params.password)
                    .addOnSuccessListener {
                        setBaseUserInfo(params.firstName, params.lastName, params.avatar) {
                            cont.resume(
                                Either.Right(Unit)
                            )
                        }
                    }
                    .addOnFailureListener { e -> cont.resume(Either.Left(e)) }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }

    private fun setBaseUserInfo(
        firstName: String,
        lastName: String,
        avatar: String,
        sucess: () -> Unit
    ) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName("$firstName $lastName")
            .setPhotoUri(Uri.parse(avatar))
            .build()

        FirebaseAuth
            .getInstance().currentUser?.let {
            it.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (!task.isSuccessful) throw RuntimeException(task.exception)
                sucess()
            }
        }
    }

    data class Params(
        val login: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val avatar: String
    )
}