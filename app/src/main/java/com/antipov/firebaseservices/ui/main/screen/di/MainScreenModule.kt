package com.antipov.firebaseservices.ui.main.screen.di

import com.antipov.firebaseservices.data.repository.NoteRepository
import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.data.repository.impl.NoteRepositoryImpl
import com.antipov.firebaseservices.data.repository.impl.UserRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.domain.notes.CreateNoteUseCase
import com.antipov.firebaseservices.domain.user.GetUserData
import com.antipov.firebaseservices.domain.user.ValidateEmailUseCase
import com.antipov.firebaseservices.ui.main.screen.MainScreenPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
abstract class MainScreenModule {

    @Module
    companion object {
        @Provides
        @PerChildFragment
        @JvmStatic
        fun providePresenter(
            createNoteUseCase: CreateNoteUseCase,
            validateEmailUseCase: ValidateEmailUseCase,
            getUserData: GetUserData,
            router: Router
        ) = MainScreenPresenter(createNoteUseCase, validateEmailUseCase, getUserData, router)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideUserDataUseCase(userRepository: UserRepository) = GetUserData(userRepository)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideVerifyEmailUseCase(userRepository: UserRepository) =
            ValidateEmailUseCase(userRepository)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideCreateNoteUseCase(notesRepository: NoteRepository) =
            CreateNoteUseCase(notesRepository)


        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideNotesRepo(storage: FirebaseFirestore): NoteRepository =
            NoteRepositoryImpl(storage)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideUserRepo(auth: FirebaseAuth): UserRepository = UserRepositoryImpl(auth)
    }
}