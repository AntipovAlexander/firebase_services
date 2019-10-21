package com.antipov.firebaseservices.ui.main.screen.di

import com.antipov.firebaseservices.data.repository.UserRepository
import com.antipov.firebaseservices.data.repository.impl.UserRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.domain.user.GetUserData
import com.antipov.firebaseservices.ui.main.screen.MainScreenPresenter
import com.google.firebase.auth.FirebaseAuth
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
        fun providePresenter(getUserData: GetUserData, router: Router) = MainScreenPresenter(getUserData, router)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideUserDataUseCase(auth: FirebaseAuth) = GetUserData(UserRepositoryImpl(auth))
    }
}