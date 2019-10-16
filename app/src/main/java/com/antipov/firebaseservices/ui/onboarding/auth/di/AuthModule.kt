package com.antipov.firebaseservices.ui.onboarding.auth.di

import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.ui.onboarding.auth.AuthPresenter
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
        fun providePresenter(router: Router) = AuthPresenter(router)
    }
}