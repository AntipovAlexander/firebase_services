package com.antipov.firebaseservices.ui.onboarding.register.di

import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.ui.onboarding.register.RegisterPresenter
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
        fun providePresenter(router: Router) = RegisterPresenter(router)
    }
}