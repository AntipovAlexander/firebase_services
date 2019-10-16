package com.antipov.firebaseservices.ui.onboarding.auth.di

import com.antipov.firebaseservices.data.repository.impl.UserRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.domain.user.AuthUserUseCase
import com.antipov.firebaseservices.ui.onboarding.auth.AuthPresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
abstract class AuthModule {

    @Module
    companion object {
        @Provides
        @PerChildFragment
        @JvmStatic
        fun providePresenter(authUseCase: AuthUserUseCase, router: Router) =
            AuthPresenter(authUseCase, router)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideHostDependency(auth: FirebaseAuth): AuthUserUseCase =
            AuthUserUseCase(UserRepositoryImpl(auth))
    }
}