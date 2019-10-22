package com.antipov.firebaseservices.domain.notes

import com.antipov.firebaseservices.Either
import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.data.repository.NoteRepository
import com.antipov.firebaseservices.domain.base.UseCase
import com.google.firebase.auth.FirebaseAuth
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetNotesUseCase(private val notesRepository: NoteRepository, private val auth: FirebaseAuth) :
    UseCase<List<Note>, Any?>() {

    override suspend fun invoke(params: Any?): Either<Exception, List<Note>> =
        suspendCoroutine { cont ->
            try {
                notesRepository
                    .getNotes(auth.uid ?: throw RuntimeException("Current user is null"))
                    .addOnSuccessListener { snapshot ->
                        val notesList = mutableListOf<Note>()
                        snapshot.forEach { document ->
                            val userId = document.get("userId") as? String ?: return@forEach
                            val title = document.get("title") as? String ?: return@forEach
                            val content = document.get("content") as? String ?: return@forEach
                            val createdAt = document.get("createdAt") as? Long ?: return@forEach
                            val note = Note(userId, title, content, createdAt)
                            notesList.add(note)
                        }
                        cont.resume(Either.Right(notesList))
                    }
                    .addOnFailureListener {
                        "".toString()
                    }
                    .addOnCanceledListener { throw RuntimeException("Action is cancelled") }
            } catch (e: Exception) {
                cont.resume(Either.Left(e))
            }
        }
}