package com.antipov.firebaseservices.ui.main.screen.di

import com.antipov.firebaseservices.di.scopes.PerChildFragment
import com.antipov.firebaseservices.ui.main.screen.MainScreenPresenter
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
        fun providePresenter(router: Router) = MainScreenPresenter(router)
    }
}