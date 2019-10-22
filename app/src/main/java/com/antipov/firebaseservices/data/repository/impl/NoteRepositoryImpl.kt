package com.antipov.firebaseservices.data.repository.impl

import com.antipov.firebaseservices.data.model.Note
import com.antipov.firebaseservices.data.repository.NoteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class NoteRepositoryImpl(
    private val store: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(uid: String) =
        store
            .collection("notes")
            .whereEqualTo("userId", uid)
            .get()

    override fun saveNote(note: Note): Task<DocumentReference> {
        val noteRecord = hashMapOf(
            "userId" to note.userId,
            "title" to note.title,
            "content" to note.content,
            "createdAt" to note.createdAt
        )

        return store.collection("notes").add(noteRecord)
    }
}