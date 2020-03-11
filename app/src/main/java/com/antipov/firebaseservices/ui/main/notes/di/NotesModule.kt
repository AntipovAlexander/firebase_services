package com.antipov.firebaseservices.ui.main.notes.di

import com.antipov.firebaseservices.data.repository.NoteRepository
import com.antipov.firebaseservices.domain.notes.GetNotesUseCase
import com.antipov.firebaseservices.ui.main.notes.NotesPresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
abstract class NotesModule {

    @Module
    companion object {
        @Provides
        @NotesScope
        @JvmStatic
        fun providePresenter(getNotesUseCase: GetNotesUseCase) = NotesPresenter(getNotesUseCase)

        @Provides
        @NotesScope
        @JvmStatic
        fun provideGetNotesUseCase(notesRepository: NoteRepository, auth: FirebaseAuth) =
            GetNotesUseCase(notesRepository, auth)
    }
}