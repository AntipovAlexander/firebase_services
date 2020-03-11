package com.antipov.firebaseservices.ui.main.flow.di

import androidx.appcompat.app.AppCompatActivity
import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.repository.NoteRepository
import com.antipov.firebaseservices.data.repository.impl.NoteRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.main.flow.MainFlowFragment
import com.antipov.firebaseservices.ui.main.flow.MainFlowPresenter
import com.antipov.firebaseservices.ui.main.notes.NotesFragment
import com.antipov.firebaseservices.ui.main.notes.di.NotesModule
import com.antipov.firebaseservices.ui.main.notes.di.NotesScope
import com.antipov.firebaseservices.ui.main.screen.MainScreenFragment
import com.antipov.firebaseservices.ui.main.screen.di.MainScreenModule
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class MainFlowModule {

    @PerChildFragment
    @ContributesAndroidInjector(modules = [MainScreenModule::class])
    abstract fun mainScreenInjectorInjector(): MainScreenFragment

    // todo: why i cant bind it in MainScreenModule???
    @NotesScope
    @ContributesAndroidInjector(modules = [NotesModule::class])
    abstract fun notesInjector(): NotesFragment

    @Module
    companion object {

        @Provides
        @PerFragment
        @JvmStatic
        @MainFlowNavigator
        fun provideFirstFlowNav(flowFragment: MainFlowFragment) =
            AppNavigator(
                flowFragment.activity as AppCompatActivity,
                flowFragment.childFragmentManager,
                R.id.mainFlowContainer
            )

        @Provides
        @PerFragment
        @JvmStatic
        fun providePresenter(router: Router) =
            MainFlowPresenter(router)

        @Provides
        @PerFragment
        @JvmStatic
        fun provideNotesRepo(storage: FirebaseFirestore): NoteRepository =
            NoteRepositoryImpl(storage)
    }
}