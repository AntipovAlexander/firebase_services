package com.antipov.firebaseservices.ui.host.di

import com.antipov.firebaseservices.R
import com.antipov.firebaseservices.data.repository.impl.UserRepositoryImpl
import com.antipov.firebaseservices.di.scopes.PerActivity
import com.antipov.firebaseservices.di.scopes.PerFragment
import com.antipov.firebaseservices.domain.user.IsUserLogged
import com.antipov.firebaseservices.navigation.AppNavigator
import com.antipov.firebaseservices.ui.host.HostActivity
import com.antipov.firebaseservices.ui.host.HostPresenter
import com.antipov.firebaseservices.ui.main.flow.MainFlowFragment
import com.antipov.firebaseservices.ui.main.flow.di.MainFlowModule
import com.antipov.firebaseservices.ui.onboarding.flow.OnboardingFlowFragment
import com.antipov.firebaseservices.ui.onboarding.flow.di.OnboardingFlowModule
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class HostModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [OnboardingFlowModule::class])
    abstract fun onboarding(): OnboardingFlowFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [MainFlowModule::class])
    abstract fun main(): MainFlowFragment

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        fun providePresenter(isUserLogged: IsUserLogged, router: Router) =
            HostPresenter(isUserLogged, router)

        @Provides
        @PerActivity
        @JvmStatic
        @HostNavigator
        fun provideMainActivityNavigator(hostActivity: HostActivity) =
            AppNavigator(hostActivity, R.id.hostContainer)

        @Provides
        @PerActivity
        @JvmStatic
        fun provideHostDependency(auth: FirebaseAuth): IsUserLogged =
            IsUserLogged(UserRepositoryImpl(auth))
    }
}