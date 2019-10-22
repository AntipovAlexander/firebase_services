package com.antipov.firebaseservices.data.repository

import com.antipov.firebaseservices.data.model.Note
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface NoteRepository {
    fun saveNote(note: Note): Task<DocumentReference>
    fun getNotes(uid: String): Task<QuerySnapshot>
}