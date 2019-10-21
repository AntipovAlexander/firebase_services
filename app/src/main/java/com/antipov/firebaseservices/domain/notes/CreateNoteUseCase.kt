package com.antipov.firebaseservices.domain.notes

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.data.repository.NoteRepository
import com.antipov.firebaseservices.domain.base.UseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CreateNoteUseCase(private val notesRepository: NoteRepository) : UseCase<Unit, Note>() {

    override suspend fun invoke(params: Note): Either<Exception, Unit> =
        suspendCoroutine { cont ->
            try {
                notesRepository
                    .saveNote(params)
                    .addOnSuccessListener { cont.resume(Either.Right(Unit)) }
                    .addOnCanceledListener { throw RuntimeException("Action is cancelled") }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }
}