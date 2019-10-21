package com.antipov.firebaseservices.ui.onboarding.register.di

import com.antipov.firebaseservices.data.repository.impl.UserRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.domain.user.RegisterUseCase
import com.antipov.firebaseservices.ui.onboarding.register.RegisterPresenter
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
abstract class RegisterModule {

    @Module
    companion object {
        @Provides
        @PerChildFragment
        @JvmStatic
        fun providePresenter(registerUseCase: RegisterUseCase, router: Router) =
            RegisterPresenter(registerUseCase, router)

        @Provides
        @PerChildFragment
        @JvmStatic
        fun provideHostDependency(auth: FirebaseAuth): RegisterUseCase =
            RegisterUseCase(UserRepositoryImpl(auth))
    }
}